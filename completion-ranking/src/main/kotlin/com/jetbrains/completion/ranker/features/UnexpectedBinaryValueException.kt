package com.jetbrains.completion.ranker.features

/**
 * @author Vitaliy.Bibaev
 */
class UnexpectedBinaryValueException(featureName: String, value: String, availableValues: Set<String>)
    : IllegalArgumentException("Feature $featureName allows $availableValues but not $value") {
}