package com.jetbrains.completion.ranker

interface CompletionRanker {
    
    fun rank(vararg features: Double): Double {
        return 0.0
    }
    
}