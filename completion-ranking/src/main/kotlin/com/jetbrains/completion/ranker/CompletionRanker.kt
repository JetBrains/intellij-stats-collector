package com.jetbrains.completion.ranker

import com.completion.ranker.models.gbt_8.MLWhiteBox

class CompletionRanker {
    private val box = MLWhiteBox()
    
    fun rank(vararg features: Double): Double {
        return box.makePredict(*features)
    }
}