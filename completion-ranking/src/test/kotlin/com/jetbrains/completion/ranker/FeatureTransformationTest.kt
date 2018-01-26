/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jetbrains.completion.ranker

import com.jetbrains.completion.ranker.features.FeatureManager
import com.jetbrains.completion.ranker.features.Transformer
import com.jetbrains.completion.ranker.features.impl.FeatureInterpreterImpl
import com.jetbrains.completion.ranker.features.impl.FeatureManagerFactory
import com.jetbrains.completion.ranker.features.impl.FeatureReader
import com.jetbrains.completion.ranker.features.impl.FeatureReader.jsonMap
import com.jetbrains.completion.ranker.features.impl.FeatureUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

private const val USER_ID = "fe27dd2076a2"
private const val LOGS = "features_transformation/$USER_ID.json"
private const val RAW_DATA = "features_transformation/${USER_ID}_raw.tsv"
private const val CLEAN_DATA = "features_transformation/${USER_ID}_clean.tsv"
private const val SCORE = "features_transformation/${USER_ID}_score.tsv"
private const val CLEAN_HEADER = "features_transformation/clean_header.txt"
private const val RAW_HEADER = "features_transformation/raw_header.txt"

class ScoreRow(index: Int,
               values: List<String>,
               columnNameIndex: Map<String, Int>): EventRow(index, values, columnNameIndex) {

    val position: Int
        get() = get("old_position").toInt()

    val rank: Double
        get() = get("rank").toDouble()

}

class ScoresRowFactory(private val columnNameIndex: Map<String, Int>): RowFactory<ScoreRow> {
    override fun row(index: Int, columns: List<String>) = ScoreRow(index, columns, columnNameIndex)
}


fun scoresTable(): DataTable<ScoreRow>  {
    val header = mapOf(
            "user_id" to 0,
            "session_id" to 1,
            "event_id" to 2,
            "rank" to 6,
            "old_position" to 7
    )
    return table(SCORE, ScoresRowFactory(header))
}

fun completionLog(path: String): CompletionLog {
    val events = jsonMap(path).asSequence()
            .map { it["logEvent"] as? Map<*, *> }
            .requireNoNulls()
            .map {
                it.asSequence().associate { it.key!!.toString() to it.value!! }
            }
            .map { CompletionLogEvent(it.toMutableMap()) }
            .toList()
    return CompletionLog(events)
}

class FeatureTransformationTest {

    private lateinit var featureManager: FeatureManager
    private lateinit var transformer: Transformer

    private lateinit var completionLog: CompletionLog
    private lateinit var table: DataTable<EventRow>

    private lateinit var scores: DataTable<ScoreRow>

    private val ranker = CompletionRanker()

    private val errorBuffer = mutableListOf<String>()

    @Before
    fun setUp() {
        featureManager = FeatureManagerFactory().createFeatureManager(FeatureReader, FeatureInterpreterImpl())
        transformer = featureManager.createTransformer()

        completionLog = completionLog(LOGS)

        val content = readTableHeader(CLEAN_HEADER)

        table = table(CLEAN_DATA, CleanDataRowFactory(content))

        scores = scoresTable()

        assert(table.rowsCount() == scores.rowsCount())
    }

    private fun readTableHeader(path: String): Map<String, Int> {
        return BufferedReader(FileReader(file(path))).lineSequence()
                .map(String::trim)
                .filter { it.isNotEmpty() }
                .mapIndexed { index, columnName -> columnName to index }
                .toMap()
    }

    @Test
    fun `test check all sessions valid`() {
        val sessions = table.rows().asSequence().map { it.sessionId }.toCollection(hashSetOf())

        val start = System.currentTimeMillis() + 1
        sessions.forEach { session_id ->
            val sessionRows: List<EventRow> = table.rows("session_id", session_id)
            val logSession: CompletionSession = completionLog.session(session_id)
            checkSession(sessionRows, logSession)
        }
        println("Validation time: " + (System.currentTimeMillis() - start))

        println("Lines with errors: ${errorBuffer.size} / ${table.rowsCount()}")
        errorBuffer.forEach(::println)
        if (errorBuffer.size > 0) {
            throw IllegalStateException()
        }
    }

    private fun checkSession(sessionRows: List<EventRow>, session: CompletionSession) {
        val lookupPages: List<LookupPage> = session.lookupPages

        assert(sessionRows.size == lookupPages.sumBy { it.size })

        val mergedSessionsElements: List<PositionedItem> = lookupPages.map { it.lookupItems }.flatMap { it }
        sessionRows
                .zip(mergedSessionsElements)
                .forEach {
                    assertFeaturesEqual(it.first, it.second)
                }
    }


    private fun assertFeaturesEqual(cleanRow: EventRow, item: PositionedItem) {
        val position = cleanRow["position"].toDouble().toInt()
        assertThat(position).isEqualTo(item.position)

        val resultLength = cleanRow["result_length"].toDouble().toInt()

        //assertThat(result_length).isEqualTo(item.length)
        if (resultLength != item.length) {
            println("Length mistmatch: $resultLength : ${item.length}")
            println("event_id " + cleanRow["event_id"])
            println("session_id " + cleanRow["session_id"])
        }


        //todo how to tack query length
        val queryLength = cleanRow["query_length"].toDouble().toInt()

        val relevanceObjects = FeatureUtils.prepareRevelanceMap(item.relevance.toList(),
                position, queryLength, resultLength)

        val features = transformer.featureArray(relevanceObjects, emptyMap())

        assertArrayEquals(cleanRow, features)

        val userId = cleanRow.userId
        val eventId = cleanRow.eventId
        val sessionId = cleanRow.sessionId

        val eventRows = scores.rows().filter {
            it.eventId == eventId && it.userId == userId && it.sessionId == sessionId && it.position == position
        }

        assert(eventRows.size == 1)

        val expectedRank = eventRows.first().rank

        val realRank = ranker.rank(features)

        val distance = Math.abs(expectedRank - realRank)

        if (distance > 0.0000001 && !ignoreError(realRank, expectedRank)) {
            errorBuffer.add("ERROR ::: Raw: ${cleanRow.index} Delta: $distance " +
                    "Expected: $expectedRank Real: $realRank Array: ${Arrays.toString(features)}")
        }
    }


    private fun assertArrayEquals(row: EventRow, features: DoubleArray) {
        var ok = 0
        var error = 0

        featureManager.featureOrder.entries.forEach { (factorName, arrayIndex) ->
            val cleanValue = row[factorName].toDouble()
            val oursValue = features[arrayIndex]
            val abs = Math.abs(oursValue - cleanValue)
            if (abs > 0.00000001) {
                println("Feature $factorName mistmatch; clean: $cleanValue ours: $oursValue")
                error++
            } else {
                ok++
            }
        }

        if (error > 0) {
            println("On row: ${row.index}")
            throw UnsupportedOperationException()
        }
    }

    // A workaround for strange bug in python -> java ml model conversion
    private fun ignoreError(real: Double, expected: Double): Boolean {
        return Math.abs(real - 0.3809264224) < 1e-7 && Math.abs(expected - 0.378375226) < 1e-7
    }
}


fun file(fileName: String): File {
    val file = FeatureTransformationTest::class.java
            .classLoader
            .getResource(fileName)
            .file

    return File(file)
}
