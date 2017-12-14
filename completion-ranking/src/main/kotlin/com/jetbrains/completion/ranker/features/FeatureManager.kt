package com.jetbrains.completion.ranker.features

import com.jetbrains.completion.ranker.features.impl.FeatureReader

/**
 * @author Vitaliy.Bibaev
 */
interface FeatureManager {
    val binaryFactors: List<BinaryFeature>
    val doubleFactors: List<DoubleFeature>
    val categorialFactors: List<CatergorialFeature>
    val ignoredFactors: Set<String>
    val featureOrder: Map<String, Int>

    val completionFactors: CompletionFactors

    fun isUserFeature(name: String): Boolean
    fun allFeatures(): List<Feature>

    fun createTransformer(): Transformer

    interface Factory {
        fun createFeatureManager(reader: FeatureReader, interpreter: FeatureInterpreter): FeatureManager
    }
}