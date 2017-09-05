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

import com.jetbrains.completion.ranker.features.LookupElementInfo
import com.jetbrains.completion.ranker.features.FeatureReader.binaryFactors
import com.jetbrains.completion.ranker.features.FeatureReader.categoricalFactors
import com.jetbrains.completion.ranker.features.FeatureReader.completionFactors
import com.jetbrains.completion.ranker.features.FeatureReader.doubleFactors
import com.jetbrains.completion.ranker.features.FeatureReader.featuresOrder
import com.jetbrains.completion.ranker.features.FeatureReader.ignoredFactors
import com.jetbrains.completion.ranker.features.FeatureReader.jsonMap
import com.jetbrains.completion.ranker.features.FeatureTransformer
import com.jetbrains.completion.ranker.features.IgnoredFactorsMatcher
import org.junit.Before
import org.junit.Test
import java.io.File
import org.assertj.core.api.Assertions.assertThat


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
    
    private lateinit var transformer: FeatureTransformer
    private lateinit var factorsOrder: Map<String, Int>

    private lateinit var completionLog: CompletionLog
    private lateinit var table: DataTable<EventRow>
    
    private lateinit var scores: DataTable<ScoreRow>
    
    private val ranker = CompletionRanker()

    private val errorBuffer = mutableListOf<String>()

    private fun featureTransformer(order: Map<String, Int>): FeatureTransformer {
        val binaryFactors = binaryFactors()
        val doubleFactors = doubleFactors()
        val categoricalFactors = categoricalFactors()
        val ignoredFactors = ignoredFactors()
        val factors = completionFactors()

        return FeatureTransformer(
                binaryFactors,
                doubleFactors,
                categoricalFactors,
                order,
                factors,
                IgnoredFactorsMatcher(ignoredFactors)
        )
    }

    @Before
    fun setUp() {
        factorsOrder = featuresOrder()
        transformer = featureTransformer(factorsOrder)

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
        val sessions = table.rows().asSequence().map { it.session_id }.toCollection(hashSetOf())

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

        val mergedSessionsElements: List<PositionedItem> = lookupPages.map { it.lookupItems }.concat()
        sessionRows
                .zip(mergedSessionsElements)
                .forEach {
                    assertFeaturesEqual(it.first, it.second)
                }
    }


    private fun assertFeaturesEqual(cleanRow: EventRow, item: PositionedItem) {
        val position = cleanRow["position"].toDouble().toInt()
        assertThat(position).isEqualTo(item.position)

        val result_length = cleanRow["result_length"].toDouble().toInt()

        //assertThat(result_length).isEqualTo(item.length)
        if (result_length != item.length) {
            println("Length mistmatch: $result_length : ${item.length}")
            println("event_id " + cleanRow["event_id"])
            println("session_id " + cleanRow["session_id"])
        }


        //todo how to tack query length
        val query_length = cleanRow["query_length"].toDouble().toInt()

        val state = LookupElementInfo(position, query_length, result_length)

        val relevanceObjects = item.relevance

        val features = transformer.featureArray(state, relevanceObjects.toMutableMap())!!


        assertArrayEquals(cleanRow, features)

        val user_id = cleanRow.user_id
        val event_id = cleanRow.event_id
        val session_id = cleanRow.session_id

        val eventRows = scores.rows().filter {
            it.event_id == event_id && it.user_id == user_id && it.session_id == session_id && it.position == position
        }

        assert(eventRows.size == 1)

        val expectedRank = eventRows.first().rank

        val realRank = ranker.rank(features)
        
        val distance = Math.abs(expectedRank - realRank)

        if (distance > 0.0000001) {
            errorBuffer.add("ERROR ::: Raw: ${cleanRow.index} Delta: $distance Expected: $expectedRank Real: $realRank")
        }
    }


    fun assertArrayEquals(row: EventRow, features: Array<Double>) {
        var ok = 0
        var error = 0

        factorsOrder.entries.forEach { (factorName, arrayIndex) ->
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


fun <T> Iterable<Iterable<T>>.concat(): List<T> {
    return fold(mutableListOf<T>(), { acc, iterable -> acc.apply { addAll(iterable) } })
}