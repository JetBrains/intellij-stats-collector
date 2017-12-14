package com.jetbrains.completion.ranker.features.ex

class FeatureDefaultValueNotFound(name: String)
    : IllegalArgumentException("Feature default value not found. Feature name: $name") {
}