package com.jetbrains.completion.ranker.features

interface FeatureInterpreter {
    fun binary(name: String, description: Map<String, Double>, order: Map<String, Int>): BinaryFeature
    fun double(name: String, defaultValue: Double, order: Map<String, Int>): DoubleFeature
    fun categorial(name: String, categories: Set<String>, order: Map<String, Int>): CatergorialFeature
}
