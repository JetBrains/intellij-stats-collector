package com.jetbrains.completion.ranker

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jetbrains.completion.ranker.features.*
import com.jetbrains.completion.test.DataTable
import com.jetbrains.completion.ranker.features.readJsonMap

import com.jetbrains.completion.ranker.features.CompletionData
import com.jetbrains.completion.ranker.features.findWithSessionUid
import com.jetbrains.completion.test.readTable
import org.junit.Before
import org.junit.Test
import java.io.File

val gson = Gson()

fun <T> MutableList<T>.merge(another: List<T>): MutableList<T> {
    addAll(another)
    return this
}

class FeatureTransformationTest {
    
    
    private lateinit var transformer: FeatureTransformer
    private lateinit var featuresOrder: Map<String, Int>

    private lateinit var rawCompletionData: CompletionData
    private lateinit var cleanTable: DataTable
    
    @Before
    fun setUp() {
        val binaryFeatures = readBinaryFeaturesInfo()
        val doubleFeatures = readDoubleFeaturesInfo()
        val categoricalFeatures = readCategoricalFeaturesInfo()
        val allFeatures = readAllFeatures()

        rawCompletionData = readJsonMap("sample_data/data.json")
        cleanTable = readTable("sample_data/0997_clean.txt")
        
        featuresOrder = readFeaturesOrder()
        transformer = FeatureTransformer(binaryFeatures, doubleFeatures, categoricalFeatures, featuresOrder, FeatureProvider(allFeatures))
    }


    @Test
    fun `test session`() {
        processSession(cleanTable, rawCompletionData, "88cdc0301a2b")
    }

    @Test
    fun `test yyy`() {
        val sessions = cleanTable.getValuesOfColumn("session_id")
        sessions.forEach {
            processSession(cleanTable, rawCompletionData, it)
        }
    }

    private fun processSession(cleanTable: DataTable,
                               rawCompletionData: CompletionData,
                               session_id: String, 
                               row: Int = -1) {

        println("Processing session: $session_id")

        val rawData: List<CompletionItem> = rawCompletionData.findWithSessionUid(session_id)
        val cleanRaws = cleanTable.getRows("session_id", session_id)

        val completionItems: Map<Int, CompletionItem> = rawData
                .mapNotNull { it["newCompletionListItems"] as? List<Any> }
                .fold<List<Any>, List<Any>>(mutableListOf<Any>(), { a, b -> a.plus(b) })
                .map { ((it as Map<String, Any>)["id"] as Double).toInt() to it }
                .toMap()

        val map: List<List<CompletionItem>> = rawData
                .mapNotNull { it["completionListIds"] as? List<Int> }
                .filter { it.isNotEmpty() }
                .map { it.map { completionItems[it]!! } }


        val fullLookupSent: MutableList<CompletionItem> = map.fold(mutableListOf<CompletionItem>(), { a, b -> a.merge(b) })

        if (cleanRaws.size != fullLookupSent.size) {
            val text = {
                "For session id: $session_id; " +
                        "Clean raws size: ${cleanRaws.size}; " +
                        "Completion lookup items: ${completionItems.size}"
            }()
            println(text)
            throw UnsupportedOperationException()
        }

        if (row > 0) {
            checkRow(fullLookupSent[row], cleanRaws[row])
            return
        }
        
        cleanRaws.zip(fullLookupSent)
                .forEach { 
                    checkRow(it.second, it.first) 
                }
    }

    private fun checkRow(relevance: CompletionItem, cleanRow: DataTable.Row) {
        val position = cleanRow.getValueOf("position").toDouble().toInt()
        val resultLength = cleanRow.getValueOf("result_length").toDouble().toInt()
        val cerpLength = cleanRow.getValueOf("cerp_length").toDouble().toInt()
        val queryLength = cleanRow.getValueOf("query_length").toDouble().toInt()
        val state = CompletionState(position, queryLength, cerpLength, resultLength)
        
        val features = transformer.toFeatureArray(state, relevance)
        checkArraysEqual(cleanRow, features)
    }

    fun checkArraysEqual(cleanRow: DataTable.Row, features: Array<Double>) {
        var ok = 0
        var error = 0

        featuresOrder.entries.forEach { (name, index) ->
            val cleanValue = cleanRow.getValueOf(name).toDouble()
            val oursValue = features[index]
            if (Math.abs(oursValue - cleanValue) > 0.01) {
                println("Feature $name mistmatch; clean: $cleanValue ours: $oursValue")
                error++
            } else {
                ok++
            }
        }

        println("Total OK: $ok ERROR: $error")

        if (error > 0) {
            println("On row: ${cleanRow.index}")
            throw UnsupportedOperationException()
        }
    }
    

    @Test
    fun `test xxx`() {
        val lookup: List<Any> = readCompletionLookup("features_transformation/completion_list.json")
        
        val state = CompletionState(0, 1, 4, 5)
        val array = transformer.toFeatureArray(state, lookup[0] as Map<String, Any>)

        val features = readFile("features_transformation/result_feature_array.txt").split("\n")

        println()
        
        val diff = array.toList().zip(features.map(String::toDouble)).map { it.first - it.second }

        println(diff.sum())

        val error = diff.indexOfFirst { Math.abs(it) > 0.1 }

        println(featuresOrder.entries.find { it.value == error })
    }


}

fun readCompletionLookup(filePath: String): List<Any> {
    val text = readFile(filePath)
    val typeToken = object : TypeToken<Map<String, Any>>() {}
    return gson.fromJson<Map<String, Any>>(text, typeToken.type)["newCompletionListItems"] as List<Any>
}


fun getFile(fileName: String): File {
    val file = FeatureTransformationTest::class.java
            .classLoader
            .getResource(fileName)
            .file

    return File(file)
}

fun readFile(fileName: String): String {
    val file = getFile(fileName)
    return file.readText()
}