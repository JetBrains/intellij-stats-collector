package com.jetbrains.completion.ranker.features

class DoubleFeatureImpl(override val name: String,
                        override val index: Int,
                        override val undefinedIndex: Int,
                        override val defaultValue: Double) : DoubleFeature {
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