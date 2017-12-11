package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface Feature {
    val name: String

    val undefinedIndex: Int

    fun process(value: Any?, featureArray: DoubleArray)
}