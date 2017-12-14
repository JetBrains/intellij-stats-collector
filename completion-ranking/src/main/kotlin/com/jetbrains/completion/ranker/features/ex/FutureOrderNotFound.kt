package com.jetbrains.completion.ranker.features.ex

class FutureOrderNotFound(name: String)
    : IllegalArgumentException("Information about feature order not found. Feature name = $name")