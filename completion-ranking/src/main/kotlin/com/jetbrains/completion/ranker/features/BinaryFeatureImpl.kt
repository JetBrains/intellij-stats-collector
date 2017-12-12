package com.jetbrains.completion.ranker.features

import com.jetbrains.completion.ranker.features.ex.UnexpectedBinaryValueException

/**
 * @author Vitaliy.Bibaev
 */
class BinaryFeatureImpl(override val name: String,
                        override val index: Int,
                        override val undefinedIndex: Int,
                        override val defaultValue: Double,
                        private val firstValue: BinaryFeature.BinaryValueDescriptor,
                        private val secondValue: BinaryFeature.BinaryValueDescriptor) : BinaryFeature {
    private fun transform(value: String): Double = when (value) {
        firstValue.key -> firstValue.mapped
        secondValue.key -> secondValue.mapped
        else -> throw UnexpectedBinaryValueException(name, value, setOf(firstValue.key, secondValue.key))
    }

    override val availableValues: Pair<String, String> = firstValue.key to secondValue.key

    override fun process(value: Any, featureArray: DoubleArray) {
        featureArray[undefinedIndex] = 0.0
        featureArray[index] = transform(value.toString())
    }

    override fun setDefaults(featureArray: DoubleArray) {
        featureArray[undefinedIndex] = 1.0
        featureArray[index] = defaultValue
    }
}