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


fun scoresTable(): DataTable  {
    val headers = listOf("uid", "session_id", "event_id", "u1", "u2", "u3", "rank", "old_position")
    return table("features_transformation/00c0af2c789d_score.tsv", headers)
}

class FeatureTransformationTest {
    
    private lateinit var transformer: FeatureTransformer
    private lateinit var factorsOrder: Map<String, Int>

    private lateinit var rawCompletionLog: List<CompletionLookupState>
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

        rawCompletionLog = jsonMap("features_transformation/00c0af2c789d.json").map { CompletionLookupState(it) }
        table = table("features_transformation/00c0af2c789d_clean.tsv", "features_transformation/clean_header.txt")

        scores = scoresTable()

        assert(table.rowsCount() == scores.rowsCount())
    }
    
    @Test
    fun `test check all sessions valid`() {
        val sessions = table.distinctColumns("session_id")

        sessions.forEach { session_id ->
            val sessionRows: List<DataTable.Row> = table.rows("session_id", session_id)
            val rawSession: List<CompletionLookupState> = rawCompletionLog.filter { it.sessionUid == session_id }
            checkSession(sessionRows, rawSession)
        }

        errorBuffer.forEach(::println)
        println("Lines with errors: ${errorBuffer.size} / ${table.rowsCount()}")

    }


    class PositionedItem(val position: Int, val item: LookupItemRelevance) {
        val relevance: Map<String, Any>
            get() = item.relevance

        val length: Int
            get() = item.intLength

        val id: Int
            get() = item.intId
    }

    class LookupState(val items: List<PositionedItem>) {
        val shownElements = items.size
    }

    private fun checkSession(sessionRows: List<DataTable.Row>, session: List<CompletionLookupState>) {
        val relevances: Map<Int, LookupItemRelevance> = session
                .mapNotNull { it.newCompletionListItems }
                .concat()
                .map { LookupItemRelevance(it) }
                .associate { it.intId to it }

        val lookupStates: List<LookupState> = session
                .mapNotNull { it.intCompletionListIds }
                .filter { it.isNotEmpty() }
                .map { ids ->
                    val items = ids.mapIndexed { index, id -> PositionedItem(index, relevances[id]!!) }
                    LookupState(items)
                }


        assert(sessionRows.size == lookupStates.sumBy { it.shownElements })


        val mergedSessionsElements: List<PositionedItem> = lookupStates.map { it.items }.concat()
        sessionRows
                .zip(mergedSessionsElements)
                .forEach {
                    assertFeaturesEqual(it.second, it.first)
                }
    }


    private fun assertFeaturesEqual(item: PositionedItem, cleanRow: DataTable.Row) {
        //todo use real values, after validation

        val position = cleanRow["position"].toDouble().toInt()
        val result_length = cleanRow["result_length"].toDouble().toInt()
        val query_length = cleanRow["query_length"].toDouble().toInt()

        val state = CompletionState(position, query_length, result_length)

        val relevanceObjects = item.relevance

        val features = transformer.featureArray(state, relevanceObjects.toMutableMap())!!


        assertArrayEquals(cleanRow, features)


        val rowIndex = cleanRow.index


        val user_id = cleanRow["user_id"]
        val event_id = cleanRow["event_id"]
        val session_id = cleanRow["session_id"]
        val old_position = cleanRow["position"]

        val eventRows = scores
                .rows()
                .filter {
                    it["event_id"] == event_id && it["uid"] == user_id && it["session_id"] == session_id && it["old_position"] == position.toString()
                }

        assert(eventRows.size == 1)

        val expectedRank = eventRows.first()["rank"].toDouble()


        val realRank = ranker.rank(features)
        
        val distance = Math.abs(expectedRank - realRank)

        if (distance > 0.000001) {
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
        else {
            println("Matched $ok / ${factorsOrder.size} values in array")
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


class CompletionLookupState(event: MutableMap<String, Any>) {
    init {
        //delegation will fail without key
        event.putIfAbsent("newCompletionListItems", emptyList<Map<String, Any>>())
        event.putIfAbsent("completionListIds", emptyList<Double>())
    }

    val newCompletionListItems: List<Map<String, Any>> by event
    val sessionUid: String by event
    val completionListIds: List<Double> by event

    val intCompletionListIds: List<Int>
        get() = completionListIds.map { it.toInt() }
}


class LookupItemRelevance(relevanceLog: Map<String, Any>) {
    val relevance: Map<String, Any> by relevanceLog
    val length: Double by relevanceLog
    val id: Double by relevanceLog

    val intId: Int
        get() = id.toInt()

    val intLength: Int
        get() = length.toInt()
}