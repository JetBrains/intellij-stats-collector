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

    @Before
    fun setUp() {
        val binaryFeatures = readBinaryFeaturesInfo()
        val doubleFeatures = readDoubleFeaturesInfo()
        val categoricalFeatures = readCategoricalFeaturesInfo()
        val featuresOrder = readFeaturesOrder()
        val allFeatures = readFeatures()
        transformer = FeatureTransformer(binaryFeatures, doubleFeatures, categoricalFeatures, featuresOrder, FeatureProvider(allFeatures))
    }

    @Test
    fun `test xxx`() {
        val lookup: List<Any> = readCompletionLookup("features_transformation/completion_list.json")
        val array = transformer.toFeatureArray(lookup[0] as Map<String, Any>)

        println()
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