package com.jetbrains.completion.ranker.features

class FeatureManagerFactory: FeatureManager.Factory {
    override fun createFeatureManager(reader: FeatureReader, interpreter: FeatureInterpreter): FeatureManager {
        val order = reader.featuresOrder()

        val binaryFactors = reader.binaryFactors()
                .map { (name, description) -> interpreter.binary(name, description, order) }
        val doubleFactors = reader.doubleFactors()
                .map { (name, defaultValue) -> interpreter.double(name, defaultValue, order) }
        val categorialFactors = reader.categoricalFactors()
                .map { (name, categories) -> interpreter.categorial(name, categories, order) }

        val completionFactors = reader.completionFactors()

        val ignoredFactors = reader.ignoredFactors()

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
            return NewFeatureTransformer(features, ignoredFactors, completionFactors, featureOrder.size)
        }
    }
}