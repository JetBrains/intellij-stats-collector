package com.jetbrains.completion.ranker

import com.jetbrains.completion.ranker.features.CompletionState
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


fun scoresTable(): DataTable  {
    val headers = listOf("uid", "session_id", "event_id", "u1", "u2", "u3", "rank", "old_position")
    return table("features_transformation/00c0af2c789d_score.tsv", headers)
}

fun completionLog(path: String): CompletionLog {
    val events = jsonMap(path).map { CompletionLogEvent(it) }
    return CompletionLog(events)
}

class FeatureTransformationTest {
    
    private lateinit var transformer: FeatureTransformer
    private lateinit var factorsOrder: Map<String, Int>

    private lateinit var completionLog: CompletionLog
    private lateinit var table: DataTable
    
    private lateinit var scores: DataTable
    
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
        table = table("features_transformation/00c0af2c789d_clean.tsv", "features_transformation/clean_header.txt")

        scores = scoresTable()

        assert(table.rowsCount() == scores.rowsCount())
    }

    @Test
    fun `test incorrect length`() {
        val session = table.rows("session_id", "c5124fb9b519")
        //val event = table.rows("event_id", "d68512374aa1")

        val logSession: CompletionSession = completionLog.session("c5124fb9b519")
        val polotno = logSession.lookupPages.map { it.lookupItems }.concat()

        val session_lengths = session.map { it["result_length"] }
        val polotno_lengths = polotno.map { it.length }


        println()
    }

    @Test
    fun `test check all sessions valid`() {
        val sessions = table.distinctColumnValues("session_id")

        sessions.forEach { session_id ->
            val sessionRows: List<DataTable.Row> = table.rows("session_id", session_id)
            val logSession: CompletionSession = completionLog.session(session_id)
            checkSession(sessionRows, logSession)
        }

        println("Lines with errors: ${errorBuffer.size} / ${table.rowsCount()}")
        errorBuffer.forEach(::println)
        if (errorBuffer.size > 0) {
            throw IllegalStateException()
        }
    }

    private fun checkSession(sessionRows: List<DataTable.Row>, session: CompletionSession) {
        val lookupPages: List<LookupPage> = session.lookupPages

        assert(sessionRows.size == lookupPages.sumBy { it.size })

        val mergedSessionsElements: List<PositionedItem> = lookupPages.map { it.lookupItems }.concat()
        sessionRows
                .zip(mergedSessionsElements)
                .forEach {
                    assertFeaturesEqual(it.first, it.second)
                }
    }


    private fun assertFeaturesEqual(cleanRow: DataTable.Row, item: PositionedItem) {
        //todo use real values, after validation

        val position = cleanRow["position"].toDouble().toInt()
        assertThat(position).isEqualTo(item.position)

        val result_length = cleanRow["result_length"].toDouble().toInt()

        //assertThat(result_length).isEqualTo(item.length)

        if (result_length != item.length) {
            println("$result_length : ${item.length}")
            println("event_id " + cleanRow["event_id"])
            println("session_id " + cleanRow["session_id"])
        }


        val query_length = cleanRow["query_length"].toDouble().toInt()


        val state = CompletionState(position, query_length, result_length)

        val relevanceObjects = item.relevance

        val features = transformer.featureArray(state, relevanceObjects.toMutableMap())!!


        assertArrayEquals(cleanRow, features)

        val user_id = cleanRow["user_id"]
        val event_id = cleanRow["event_id"]
        val session_id = cleanRow["session_id"]

        val eventRows = scores
                .rows()
                .filter {
                    it["event_id"] == event_id && it["uid"] == user_id && it["session_id"] == session_id && it["old_position"] == position.toString()
                }

        assert(eventRows.size == 1)

        val expectedRank = eventRows.first()["rank"].toDouble()


        val realRank = ranker.rank(features)
        
        val distance = Math.abs(expectedRank - realRank)

        if (distance > 0.0000001) {
            errorBuffer.add("ERROR ::: Raw: ${cleanRow.index} Delta: $distance Expected: $expectedRank Real: $realRank")
        }
    }


    fun assertArrayEquals(row: DataTable.Row, features: Array<Double>) {
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