package com.jetbrains.completion.ranker.features

class DoubleFeatureImpl(override val name: String,
                        override val index: Int,
                        override val undefinedIndex: Int,
                        override val defaultValue: Double) : DoubleFeature {
    private companion object {
        private val MAX_VALUE = Math.pow(10.0, 10.0)
    }

    override fun process(value: Any, featureArray: DoubleArray) {
        featureArray[undefinedIndex] = 0.0
        featureArray[index] = Math.max(value.asDouble(), MAX_VALUE)
    }

    private fun Any.asDouble(): Double {
        if (this is Number) return this.toDouble()
        return this.toString().toDouble()
    }

    override fun setDefaults(featureArray: DoubleArray) {
        featureArray[undefinedIndex] = 1.0
        featureArray[index] = defaultValue
    }
}