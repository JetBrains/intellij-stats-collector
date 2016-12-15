package com.jetbrains.completion.ranker.features

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


typealias DoubleFeatureInfo = Map<String, Double>
typealias CategoricalFeatureInfo = Map<String, Set<String>>
typealias BinaryFeatureInfo = Map<String, Map<String, Double>>

private val gson = Gson()

object FeaturesConstants {
    val UNDEFINED = "UNDEFINED"
    val OTHER = "OTHER"

    val RELEVANCE = "relevance"
    val PROXIMITY = "proximity"
}

class Features(val relevance: Set<String>, val proximity: Set<String>)

fun readFeatures(): Features {
    val text = readFile("features/all_features.json")
    val typeToken = object : TypeToken<Map<String, Set<String>>>() {}
    val map = gson.fromJson<Map<String, Set<String>>>(text, typeToken.type)
    val relevance = map[FeaturesConstants.RELEVANCE] ?: emptySet()
    val proximity = map[FeaturesConstants.PROXIMITY] ?: emptySet()
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

private fun readFile(fileName: String): String {
    val file = gson.javaClass.classLoader.getResource(fileName).file
    val text = File(file).readText()
    return text
}