package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
class BinaryFeatureImpl(override val name: String,
                        override val index: Int,
                        override val undefinedIndex: Int,
                        override val defaultValue: Double,
                        private val valueMapping: Map<String, Double>) : BinaryFeature {
    override fun transform(value: String): Double = valueMapping[value]
            ?: throw UnexpectedBinaryValueException(name, value, valueMapping.keys)
}