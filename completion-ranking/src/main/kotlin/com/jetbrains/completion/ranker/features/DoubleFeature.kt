package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface DoubleFeature : Feature {
    val defaultValue: Double

    val index: Int

    override fun process(value: Any?, featureArray: DoubleArray) {
        if (value == null) {
            featureArray[undefinedIndex] = 1.0
            featureArray[index] = defaultValue
        } else {
            featureArray[undefinedIndex] = 0.0
            featureArray[index] = value.asDouble()
        }
    }

    private fun Any.asDouble(): Double {
        if (this is Double) return this
        return this.toString().toDouble()
    }
}