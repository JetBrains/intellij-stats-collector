package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface FeatureManager {
    val binaryFactors: List<BinaryFeature>
    val doubleFactors: List<DoubleFeature>
    val categorialFactors: List<CatergorialFeature>
    val ignoredFactors: Set<String>

    val completionFactors: CompletionFactors
    val featureArrayLength: Int

    fun isUserFeature(name: String): Boolean
    fun allFeatures(): List<Feature>
}