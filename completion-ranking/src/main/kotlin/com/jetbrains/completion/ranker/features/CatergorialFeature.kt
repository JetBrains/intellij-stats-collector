package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface CatergorialFeature : Feature {
    val categories: Set<String>

    val otherCatergoryIndex: Int

    fun indexByCategory(category: String): Int

    override fun process(value: Any?, featureArray: DoubleArray) {
        initialize(featureArray)
        if (value == null) {
            featureArray[undefinedIndex] = 1.0
        } else {
            featureArray[indexByCategory(value.toString())] = 1.0
        }
    }

    private fun initialize(featureArray: DoubleArray) {
        categories.forEach { featureArray[indexByCategory(it)] = 0.0 }
        featureArray[undefinedIndex] = 0.0
        featureArray[otherCatergoryIndex] = 0.0
    }
}