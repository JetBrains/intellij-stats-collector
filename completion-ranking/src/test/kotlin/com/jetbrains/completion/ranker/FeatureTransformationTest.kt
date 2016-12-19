package com.jetbrains.completion.ranker

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jetbrains.completion.ranker.features.*
import org.junit.Before
import org.junit.Test
import java.io.File

private val gson = Gson()

class FeatureTransformationTest {
    
    
    private lateinit var transformer: FeatureTransformer
    private lateinit var featuresOrder: Map<String, Int>

    @Before
    fun setUp() {
        val binaryFeatures = readBinaryFeaturesInfo()
        val doubleFeatures = readDoubleFeaturesInfo()
        val categoricalFeatures = readCategoricalFeaturesInfo()
        val allFeatures = readAllFeatures()
        featuresOrder = readFeaturesOrder()
        transformer = FeatureTransformer(binaryFeatures, doubleFeatures, categoricalFeatures, featuresOrder, FeatureProvider(allFeatures))
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


fun readFile(fileName: String): String {
    val file = FeatureTransformationTest::class.java
            .classLoader
            .getResource(fileName)
            .file

    val text = File(file).readText()
    return text
}