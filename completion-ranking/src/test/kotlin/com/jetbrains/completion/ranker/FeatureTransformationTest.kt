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

import com.jetbrains.completion.ranker.features.*
import com.jetbrains.completion.ranker.features.impl.FeatureInterpreterImpl
import com.jetbrains.completion.ranker.features.impl.FeatureManagerFactory
import com.jetbrains.completion.ranker.features.impl.FeatureReader
import com.jetbrains.completion.ranker.features.impl.FeatureReader.jsonMap
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.io.File


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
    return table("features_transformation/00c0af2c789d_score.tsv", ScoresRowFactory(header))
}

fun completionLog(path: String): CompletionLog {
    val events = jsonMap(path).map { CompletionLogEvent(it) }
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

        completionLog = completionLog("features_transformation/00c0af2c789d.json")

        val content = readTableHeader("features_transformation/clean_header.txt")

        table = table("features_transformation/00c0af2c789d_clean.tsv", CleanDataRowFactory(content))

        scores = scoresTable()

        assert(table.rowsCount() == scores.rowsCount())
    }

    private fun readTableHeader(path: String): Map<String, Int> {
        return file(path).readText()
                .split("\n")
                .map(String::trim)
                .filter { it.isNotEmpty() }
                .mapIndexed { index, columnName -> columnName to index }
                .toMap()
    }

    @Test
    fun `test check all sessions valid`() {
        val sessions = table.rows().asSequence().map { it.sessionId }.toCollection(hashSetOf())

        val start = System.currentTimeMillis()
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

        val relevanceObjects = item.relevance.toMutableMap()
        relevanceObjects.put("position", position)
        relevanceObjects.put("query_length", queryLength)
        relevanceObjects.put("result_length", resultLength)

        val features = transformer.featureArray(relevanceObjects, emptyMap())!!


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

        if (distance > 0.0000001) {
            errorBuffer.add("ERROR ::: Raw: ${cleanRow.index} Delta: $distance Expected: $expectedRank Real: $realRank")
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
}


fun file(fileName: String): File {
    val file = FeatureTransformationTest::class.java
            .classLoader
            .getResource(fileName)
            .file

    return File(file)
}
