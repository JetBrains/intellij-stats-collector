package com.jetbrains.completion.ranker

import com.jetbrains.completion.ranker.features.*
import com.jetbrains.completion.ranker.features.impl.FeatureInterpreterImpl
import com.jetbrains.completion.ranker.features.impl.FeatureManagerFactory
import com.jetbrains.completion.ranker.features.impl.FeatureReader
import com.jetbrains.completion.ranker.features.impl.FeatureUtils
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Vitaliy.Bibaev
 */
class ModelMetadataTest {
    @Test
    fun `validate feature order`() {
        val manager = featureManager()
        val featureIndex: Map<String, Feature> = mutableMapOf<String, Feature>().apply {
            manager.binaryFactors.forEach { put(it.name, it) }
            manager.doubleFactors.forEach { put(it.name, it) }
            manager.categorialFactors.forEach { put(it.name, it) }
        }

        for ((name, order) in manager.featureOrder) {
            val split = name.split('=')
            if (split.size > 3) Assert.fail("line '$name' in feature order contains more than 1 symbol '='")
            if (split.size == 2) {
                val (featureName, value) = split
                val feature = featureIndex[featureName]
                        ?: throw AssertionError("feature declared in the featureOrder not found: $featureName")
                when (feature) {
                    is BinaryFeature -> {
                        assertEquals(FeatureUtils.UNDEFINED, value)
                        assertEquals(order, feature.undefinedIndex)
                    }
                    is DoubleFeature -> {
                        assertEquals(FeatureUtils.UNDEFINED, value)
                        assertEquals(order, feature.undefinedIndex)
                    }
                    is CatergorialFeature -> {
                        when (value) {
                            FeatureUtils.UNDEFINED -> assertEquals(order, feature.undefinedIndex)
                            FeatureUtils.OTHER -> assertEquals(order, feature.otherCatergoryIndex)
                            else -> assertEquals(order, feature.indexByCategory(value))
                        }
                    }
                    else -> fail("unknown feature type found: ${feature.javaClass.canonicalName}")
                }
            } else {
                val featureName = split.single()
                val feature = featureIndex[featureName]
                        ?: throw AssertionError("feature declared in the featureOrder not found: $featureName")
                when (feature) {
                    is BinaryFeature -> assertEquals(order, feature.index)
                    is DoubleFeature -> assertEquals(order, feature.index)
                    is CatergorialFeature -> fail("categorial feature claims 'name=category' in feature order list")
                    else -> fail("unknown feature type found: ${feature.javaClass.canonicalName}")
                }
            }
        }
    }

    @Test
    fun `test all features have distinct names`() {
        val manager = featureManager()
        val featuresByName = mutableListOf<Feature>().apply {
            addAll(manager.binaryFactors)
            addAll(manager.doubleFactors)
            addAll(manager.categorialFactors)
        }.groupBy { it.name }

        var failed = false
        for ((name, features) in featuresByName) {
            if (features.size > 1) {
                println("${features.size} features with name '$name found'")
                failed = true
            }
        }

        assertFalse("features with the same name found (see output above)", failed)

    }

    @Test
    fun `test features fill features array properly`() {
        val manager = featureManager()
        val revertedFeatureOrder = mutableMapOf<Int, Feature>()
        fun MutableMap<Int, Feature>.checkAndPut(index: Int, feature: Feature) {
            assertFeaturesNotStoreValueBySameIndex(index, put(index, feature), feature)
        }
        for (feature in manager.binaryFactors) {
            revertedFeatureOrder.checkAndPut(feature.index, feature)
            revertedFeatureOrder.checkAndPut(feature.undefinedIndex, feature)
        }

        for (feature in manager.doubleFactors) {
            revertedFeatureOrder.checkAndPut(feature.index, feature)
            revertedFeatureOrder.checkAndPut(feature.undefinedIndex, feature)
        }

        for (feature in manager.categorialFactors) {
            revertedFeatureOrder.checkAndPut(feature.otherCatergoryIndex, feature)
            feature.categories.forEach {
                revertedFeatureOrder.checkAndPut(feature.indexByCategory(it), feature)
            }
        }

        assertEquals("features should totally cover the features array", manager.featureOrder.size, revertedFeatureOrder.size)
        assertEquals(0, revertedFeatureOrder.keys.min())
        assertEquals(manager.featureOrder.size - 1, revertedFeatureOrder.keys.max())
    }

    private fun assertFeaturesNotStoreValueBySameIndex(index: Int, old: Feature?, new: Feature) {
        if (old != null) {
            fail("features store values to the same array element [index = $index, names = {${old.name}, ${new.name}}]")
        }
    }

    private fun featureManager(): FeatureManager =
            FeatureManagerFactory().createFeatureManager(FeatureReader, FeatureInterpreterImpl())
}