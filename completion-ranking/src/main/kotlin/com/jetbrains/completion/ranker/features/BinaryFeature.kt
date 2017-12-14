package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface BinaryFeature : Feature {
    val defaultValue: Double

    val availableValues: Pair<String, String>

    val index: Int

    data class BinaryValueDescriptor(val key: String, val mapped: Double)
}