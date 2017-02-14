package com.jetbrains.completion.ranker.features

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.net.URL


typealias DoubleFeatureInfo = Map<String, Double>
typealias CategoricalFeatureInfo = Map<String, Set<String>>
typealias BinaryFeatureInfo = Map<String, Map<String, Double>>

val gson = Gson()

object FeatureUtils {
    val UNDEFINED = "UNDEFINED"
    val OTHER = "OTHER"

    val RELEVANCE = "relevance"
    val PROXIMITY = "proximity"
    
    val DEFAULT = "default"
    
    fun getUndefinedFeatureName(name: String) = "$name=$UNDEFINED"
}

class Features(val relevance: Set<String>, val proximity: Set<String>)

fun readAllFeatures(): Features {
    val text = readFile("features/all_features.json")
    val typeToken = object : TypeToken<Map<String, Set<String>>>() {}
    val map = gson.fromJson<Map<String, Set<String>>>(text, typeToken.type)
    val relevance = map[FeatureUtils.RELEVANCE] ?: emptySet()
    val proximity = map[FeatureUtils.PROXIMITY] ?: emptySet()
    return Features(relevance, proximity)
}

fun readBinaryFeaturesInfo(): BinaryFeatureInfo {
    val text = readFile("features/binary.json")
    val typeToken = object : TypeToken<BinaryFeatureInfo>() {}
    return gson.fromJson<BinaryFeatureInfo>(text, typeToken.type)
}

fun readCategoricalFeaturesInfo(): CategoricalFeatureInfo {
    val text = readFile("features/categorical.json")
    val typeToken = object : TypeToken<CategoricalFeatureInfo>() {}
    return gson.fromJson<CategoricalFeatureInfo>(text, typeToken.type)
}

fun readDoubleFeaturesInfo(): DoubleFeatureInfo {
    val text = readFile("features/float.json")
    val typeToken = object : TypeToken<DoubleFeatureInfo>() {}
    return gson.fromJson<DoubleFeatureInfo>(text, typeToken.type)
}

fun readFeaturesOrder(): Map<String, Int> {
    val text = readFile("features/final_features_order.txt")
    
    var index = 0
    val map = mutableMapOf<String, Int>()
    text.split("\n").forEach { 
        val featureName = it.trim()
        map[featureName] = index++
    }
    
    return map
}

private fun readFile(fileName: String): String {
    val fileStream = gson.javaClass.classLoader.getResourceAsStream(fileName)
    return fileStream.reader().readText()
}

fun String.toRelevanceMap(): Map<String, Any> {
    val items = replace("[", "").replace("]", "").split(",")

    return items.map { 
        val (key, value) = it.trim().split("=")
        key to value
    }.toMap()
    
}

typealias CompletionData = List<Map<String, Any>>

typealias CompletionItem = Map<String, Any>

fun CompletionData.findWithSessionUid(sessionUid: String): List<CompletionItem> = filter { it["sessionUid"] == sessionUid }

fun readJsonMap(fileName: String): CompletionData {
    val text = readFile(fileName)
    val typeToken = object : TypeToken<CompletionData>() {}
    return gson.fromJson<CompletionData>(text, typeToken.type)
}