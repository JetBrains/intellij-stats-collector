package com.jetbrains.completion.ranker

import com.completion.ranker.models.gbt_8.MLWhiteBox

class CompletionRanker {
    private val box = MLWhiteBox()
    
    fun rank(features: Array<Double>): Double {
        return box.makePredict(*features.toDoubleArray())
    }
    
    companion object {
        val rankerVersion = "gbt_8" 
    }
}