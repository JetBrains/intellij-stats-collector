package com.jetbrains.completion.ranker.features

class CatergorialFeatureImpl(override val name: String,
                             override val undefinedIndex: Int,
                             override val otherCatergoryIndex: Int,
                             private val categoryToIndex: Map<String, Int>)
    : CatergorialFeature {
    override fun indexByCategory(category: String): Int = categoryToIndex[category] ?: otherCatergoryIndex

    override val categories: Set<String> = categoryToIndex.keys
}