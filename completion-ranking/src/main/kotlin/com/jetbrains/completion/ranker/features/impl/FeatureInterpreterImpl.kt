package com.jetbrains.completion.ranker.features.impl

import com.jetbrains.completion.ranker.features.*
import com.jetbrains.completion.ranker.features.BinaryFeature.BinaryValueDescriptor
import com.jetbrains.completion.ranker.features.ex.FeatureDefaultValueNotFound
import com.jetbrains.completion.ranker.features.ex.FutureOrderNotFound

class FeatureInterpreterImpl : FeatureInterpreter {
    override fun binary(name: String, description: Map<String, Double>, order: Map<String, Int>): BinaryFeature {
        val index = extractIndex(name, order)
        val undefinedIndex = extractUndefinedIndex(name, order)
        val default = description[FeatureUtils.DEFAULT] ?: throw FeatureDefaultValueNotFound(name)
        val values = extractBinaryValuesMapping(description)
        return BinaryFeatureImpl(name, index, undefinedIndex, default, values.first, values.second)
    }

    override fun double(name: String, defaultValue: Double, order: Map<String, Int>): DoubleFeature {
        val index = extractIndex(name, order)
        val undefinedIndex = extractUndefinedIndex(name, order)
        return DoubleFeatureImpl(name, index, undefinedIndex, defaultValue)
    }

    override fun categorial(name: String, categories: Set<String>, order: Map<String, Int>): CatergorialFeature {
        val undefinedIndex = extractUndefinedIndex(name, order)
        val otherCategoryIndex = extractIndex(FeatureUtils.getOtherCategoryFeatureName(name), order)
        val categoryToIndex = categories.associate { it to extractIndex(combine(name, it), order) }
        return CatergorialFeatureImpl(name, undefinedIndex, otherCategoryIndex, categoryToIndex)
    }

    private fun extractIndex(name: String, order: Map<String, Int>): Int {
        return order[name] ?: throw FutureOrderNotFound(name)
    }

    private fun extractUndefinedIndex(name: String, order: Map<String, Int>): Int {
        return extractIndex(FeatureUtils.getUndefinedFeatureName(name), order)
    }

    private fun extractBinaryValuesMapping(description: Map<String, Double>)
            : Pair<BinaryValueDescriptor, BinaryValueDescriptor> {
        val result = mutableListOf<BinaryValueDescriptor>()
        for ((name, value) in description) {
            if (name == FeatureUtils.DEFAULT) continue
            result += BinaryValueDescriptor(name, value)
        }

        assert(result.size == 2, { "binary feature must contains 2 values, but found $result" })
        result.sortBy { it.key }
        return result[0] to result[1]
    }

    private fun combine(featureName: String, categoryName: String): String = "$featureName=$categoryName"
}