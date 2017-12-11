package com.jetbrains.completion.ranker.features

class DoubleFeatureImpl(override val name: String,
                        override val index: Int,
                        override val undefinedIndex: Int,
                        override val defaultValue: Double) : DoubleFeature