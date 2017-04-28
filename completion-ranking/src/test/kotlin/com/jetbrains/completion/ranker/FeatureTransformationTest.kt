package com.jetbrains.completion.ranker

import com.jetbrains.completion.ranker.features.*
import com.jetbrains.completion.ranker.features.FeatureReader.binaryFactors
import com.jetbrains.completion.ranker.features.FeatureReader.categoricalFactors
import com.jetbrains.completion.ranker.features.FeatureReader.completionFactors
import com.jetbrains.completion.ranker.features.FeatureReader.doubleFactors
import com.jetbrains.completion.ranker.features.FeatureReader.featuresOrder
import com.jetbrains.completion.ranker.features.FeatureReader.ignoredFactors
import com.jetbrains.completion.ranker.features.FeatureReader.jsonMap
import com.jetbrains.completion.test.DataTable
import com.jetbrains.completion.test.table
import org.junit.Before
import org.junit.Test
import java.io.File


fun scores(): List<Double>  {
    val file = file("features_transformation/00c0af2c789d_score.tsv")
    return file.readLines().map { it.split("\t")[6].trim().toDouble() }
}

class FeatureTransformationTest {
    
    private lateinit var transformer: FeatureTransformer
    private lateinit var order: Map<String, Int>

    private lateinit var rawCompletionData: List<CompletionLookupState>
    private lateinit var cleanTable: DataTable
    
    private lateinit var scores: List<Double>
    
    private val ranker = CompletionRanker()


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
        order = featuresOrder()
        transformer = featureTransformer(order)

        rawCompletionData = jsonMap("features_transformation/00c0af2c789d.json").map { CompletionLookupState(it) }
        cleanTable = table("features_transformation/00c0af2c789d_clean.tsv", "features_transformation/clean_header.txt")

        scores = scores()
    }
    
    @Test
    fun `test check all sessions valid`() {
        val sessions = cleanTable.distinctSessions("session_id")
        sessions.forEach {
            checkSession(cleanTable, rawCompletionData, it)
        }
        println("Total rows: ${cleanTable.getRowsCount()}")
    }


    class PositionedItem(val position: Int, val itemRelevance: LookupItemRelevance)
    class LookupState(val items: List<PositionedItem>) {
        val shownElements = items.size
    }

    private fun checkSession(cleanTable: DataTable, log: List<CompletionLookupState>, session_id: String) {
        val session: List<CompletionLookupState> = log.filter { it.sessionUid == session_id }
        val cleanRaws = cleanTable.rows("session_id", session_id)

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


        assert(cleanRaws.size == lookupStates.sumBy { it.shownElements })


//        val mergedSessionsElements: List<PositionedItem> = lookupStates.map { it.items }.concat()
//        cleanRaws
//                .zip(mergedSessionsElements)
//                .forEach {
//                    assertFeaturesEqual(it.second, it.first)
//                }
    }


    private fun assertFeaturesEqual(relevance: PositionedItem, cleanRow: DataTable.Row) {
        //val position = cleanRow.valueOf("position").toDouble().toInt()
        //val resultLength = cleanRow.valueOf("result_length").toDouble().toInt()
        val position = relevance.position
        val resultLength = relevance.itemRelevance.intLength
        //val prefixLength = relevance.itemRelevance

        val queryLength = cleanRow.valueOf("query_length").toDouble().toInt()
        val state = CompletionState(position, queryLength, resultLength)

        val preparedRelevanceMap = emptyMap<String, Any>()
        TODO("repair")

        val features = transformer.featureArray(state, preparedRelevanceMap)!!
        
        checkArraysEqual(cleanRow, features)

        val rowIndex = cleanRow.index
        val expectedRank = scores[rowIndex]
        val realRank = ranker.rank(features)
        
        val distance = Math.abs(expectedRank - realRank)

        if (distance > 0.0000000000001) {
            println("Raw: ${cleanRow.index} Distance: $distance Expected: $expectedRank Real: $realRank")
        }
    }

    fun checkArraysEqual(cleanRow: DataTable.Row, features: Array<Double>) {
        var ok = 0
        var error = 0

        order.entries.forEach { (name, index) ->
            val cleanValue = cleanRow.valueOf(name).toDouble()
            val oursValue = features[index]
            if (Math.abs(oursValue - cleanValue) > 0.00000001) {
                println("Feature $name mistmatch; clean: $cleanValue ours: $oursValue")
                error++
            } else {
                ok++
            }
        }

        if (error > 0) {
            println("On row: ${cleanRow.index}")
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