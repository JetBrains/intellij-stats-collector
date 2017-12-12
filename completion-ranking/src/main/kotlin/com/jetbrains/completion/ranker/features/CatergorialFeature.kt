package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
interface CatergorialFeature : Feature {
    val categories: Set<String>

    val otherCatergoryIndex: Int

    fun indexByCategory(category: String): Int
}