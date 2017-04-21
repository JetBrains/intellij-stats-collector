package com.jetbrains.completion.ranker.features


class CompletionFactors(proximity: Set<String>, relevance: Set<String>) {
    
    private val knownFactors: Set<String> = HashSet<String>().apply {
        addAll(proximity.map { "prox_$it" })
        addAll(relevance)
    }

    fun unknownFactors(factore: Set<String>): Set<String> {
        return factore.subtract(knownFactors) 
    }
    
}


class IgnoredFactorsMatcher(private val ignoredFactors: Set<String>) {
    fun ignore(factor: String): Boolean {
        val normalized = factor.substringBefore('@')
        return ignoredFactors.contains(normalized)
    }
}