package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface Transformer {
    fun featureArray(info: LookupElementInfo, relevanceObjects: Map<String, Any?>,
                     userFactors: Map<String, Any?>): DoubleArray?
}