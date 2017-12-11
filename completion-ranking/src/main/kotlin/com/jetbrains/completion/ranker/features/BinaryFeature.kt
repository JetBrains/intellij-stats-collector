package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface BinaryFeature : Feature {
    val defaultValue: Double

    val index: Int

    fun transform(value: String): Double

    override fun process(value: Any?, featureArray: DoubleArray) {
        if (value == null) {
            featureArray[undefinedIndex] = 1.0
            featureArray[index] = defaultValue
        } else {
            featureArray[undefinedIndex] = 0.0
            featureArray[index] = transform(value.toString())
        }
    }
}