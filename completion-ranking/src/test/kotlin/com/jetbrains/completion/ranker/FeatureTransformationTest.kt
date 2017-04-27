package com.jetbrains.completion.ranker

import com.jetbrains.completion.ranker.features.*
import com.jetbrains.completion.ranker.features.FeatureReader.binaryFactors
import com.jetbrains.completion.ranker.features.FeatureReader.categoricalFactors
import com.jetbrains.completion.ranker.features.FeatureReader.completionFactors
import com.jetbrains.completion.ranker.features.FeatureReader.doubleFactors
import com.jetbrains.completion.ranker.features.FeatureReader.ignoredFactors
import com.jetbrains.completion.ranker.features.FeatureReader.readJsonMap
import com.jetbrains.completion.test.DataTable
import com.jetbrains.completion.test.readTable
import org.junit.Before
import org.junit.Test
import java.io.File


fun <T> MutableList<T>.merge(another: List<T>): MutableList<T> {
    addAll(another)
    return this
}

fun readScores(): List<Double>  {
    val file = getFile("features_transformation/0997_score_true.txt")
    return file.readLines()
            .map { it.split("\t")[6].trim().toDouble() }
}


class FeatureTransformationTest {
    
    private lateinit var transformer: FeatureTransformer
    private lateinit var order: Map<String, Int>

    private lateinit var rawCompletionData: CompletionData
    private lateinit var cleanTable: DataTable
    
    private lateinit var scores: List<Double>
    
    private val ranker = CompletionRanker()
    
    @Before
    fun setUp() {
        val binaryFactors = binaryFactors()
        val doubleFactors = doubleFactors()
        val categoricalFactors = categoricalFactors()
        val ignoredFactors = ignoredFactors()
        
        val factors = completionFactors()

        rawCompletionData = readJsonMap("sample_data/data.json")
        cleanTable = readTable("sample_data/0997_clean.txt")
        
        order = FeatureReader.featuresOrder()
        scores = readScores()

        transformer = FeatureTransformer(
                binaryFactors,
                doubleFactors,
                categoricalFactors,
                order,
                factors,
                IgnoredFactorsMatcher(ignoredFactors)
        )
        
    }
    
    @Test
    fun `test check all sessions valid`() {
        val sessions = cleanTable.getValuesOfColumn("session_id")
        sessions.forEach {
            checkSession(cleanTable, rawCompletionData, it)
        }
        println("Total rows: ${cleanTable.getRowsCount()}")
    }

    private fun checkSession(cleanTable: DataTable,
                             rawCompletionData: CompletionData,
                             session_id: String) {
        
        val rawData: List<Map<String, Any>> = rawCompletionData.findWithSessionUid(session_id)
        val cleanRaws = cleanTable.getRows("session_id", session_id)

        val completionItems: Map<Int, Map<String, Any>> = rawData
                .mapNotNull { it["newCompletionListItems"] as? List<Any> }
                .fold<List<Any>, List<Any>>(mutableListOf<Any>(), { a, b -> a.plus(b) })
                .map { ((it as Map<String, Any>)["id"] as Double).toInt() to it }
                .toMap()

        val map: List<List<Map<String, Any>>> = rawData
                .mapNotNull { it["completionListIds"] as? List<Int> }
                .filter { it.isNotEmpty() }
                .map { it.map { completionItems[it]!! } }


        val fullLookupSent: MutableList<Map<String, Any>> = map.fold(mutableListOf<Map<String, Any>>(), { a, b -> a.merge(b) })

        assert(cleanRaws.size == fullLookupSent.size, { "For session id: $session_id;" +
                " Clean raws size: ${cleanRaws.size};" +
                " Completion lookup items: ${completionItems.size}" })
        
        cleanRaws.zip(fullLookupSent)
                .forEach { 
                    assertFeaturesEqual(it.second, it.first)
                }
    }
    
    
    private fun assertFeaturesEqual(relevance: Map<String, Any>, cleanRow: DataTable.Row) {
        val position = cleanRow.getValueOf("position").toDouble().toInt()
        val resultLength = cleanRow.getValueOf("result_length").toDouble().toInt()
        val queryLength = cleanRow.getValueOf("query_length").toDouble().toInt()
        val state = CompletionState(position, queryLength, resultLength)
        
        val features = transformer.featureArray(state, relevance)!!
        
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
            val cleanValue = cleanRow.getValueOf(name).toDouble()
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

fun getFile(fileName: String): File {
    val file = FeatureTransformationTest::class.java
            .classLoader
            .getResource(fileName)
            .file

    return File(file)
}