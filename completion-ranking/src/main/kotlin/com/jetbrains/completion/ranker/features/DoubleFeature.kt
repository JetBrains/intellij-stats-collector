package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface DoubleFeature : Feature {
    val defaultValue: Double

    val index: Int
}