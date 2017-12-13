package com.jetbrains.completion.ranker.features.impl

import com.jetbrains.completion.ranker.features.*

class FeatureManagerFactory: FeatureManager.Factory {
    override fun createFeatureManager(reader: FeatureReader, interpreter: FeatureInterpreter): FeatureManager {
        val order = FeatureReader.featuresOrder()

        val binaryFactors = FeatureReader.binaryFactors()
                .map { (name, description) -> interpreter.binary(name, description, order) }
        val doubleFactors = FeatureReader.doubleFactors()
                .map { (name, defaultValue) -> interpreter.double(name, defaultValue, order) }
        val categorialFactors = FeatureReader.categoricalFactors()
                .map { (name, categories) -> interpreter.categorial(name, categories, order) }

        val completionFactors = FeatureReader.completionFactors()

        val ignoredFactors = FeatureReader.ignoredFactors()

        return MyFeatureManager(binaryFactors, doubleFactors, categorialFactors, ignoredFactors, completionFactors, order)
    }

    private class MyFeatureManager(override val binaryFactors: List<BinaryFeature>,
                                   override val doubleFactors: List<DoubleFeature>,
                                   override val categorialFactors: List<CatergorialFeature>,
                                   override val ignoredFactors: Set<String>,
                                   override val completionFactors: CompletionFactors,
                                   override val featureOrder: Map<String, Int>) : FeatureManager {
        override fun isUserFeature(name: String): Boolean = false

        override fun allFeatures(): List<Feature> = ArrayList<Feature>().apply {
            addAll(binaryFactors)
            addAll(doubleFactors)
            addAll(categorialFactors)
        }

        override fun createTransformer(): Transformer {
            val features = allFeatures().associate { it.name to it }
            return FeatureTransformer(features, ignoredFactors, completionFactors, featureOrder.size)
        }
    }
}