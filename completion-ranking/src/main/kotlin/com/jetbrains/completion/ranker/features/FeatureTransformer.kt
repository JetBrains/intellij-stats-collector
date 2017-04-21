package com.jetbrains.completion.ranker.features



/**
 * position - position inside lookup
 * query_length - length of completion prefix filter
 * result_length - length of lookup element string
 */
data class CompletionState(val position: Int?,
                           val query_length: Int?,
                           val result_length: Int?)


class FeatureTransformer(private val binaryFeatures: BinaryFeatureInfo, 
                         private val doubleFeatures: DoubleFeatureInfo, 
                         private val categoricalFeatures: CategoricalFeatureInfo,
                         private val featuresOrder: Map<String, Int>,
                         private val factors: CompletionFactors,
                         private val ignoredFactorsMatcher: IgnoredFactorsMatcher) {

    companion object {
        private val MAX_DOUBLE_VALUE = Math.pow(10.0, 10.0)
    }
    
    private val featureArray: Array<Double> = Array(featuresOrder.size, { 0.0 })

    /**
     * @param preparedRelevanceMap prepared relevance map, where proximity factors names are transformed to prox_${old_name}
     * to prevent from clashing with relevance keys 
     */
    fun featureArray(state: CompletionState, preparedRelevanceMap: Map<String, Any>): Array<Double>? {
        val unknownFactors: Set<String> = factors.unknownFactors(preparedRelevanceMap.keys)
        val ignoredFactorsSize = unknownFactors.count { ignoredFactorsMatcher.ignore(it) }
        
        if (unknownFactors.size != ignoredFactorsSize) return null
        
        resetArray()
        
        preparedRelevanceMap.asSequence()
                .select { it.key !in unknownFactors }
                .forEach { processFeature(it.key, it.value) }
        
        processCompletionState(state)
        
        return featureArray
    }

    
    private fun processCompletionState(state: CompletionState) {
        val features = listOf(
                "position" to state.position, 
                "query_length" to state.query_length, 
                "result_length" to state.result_length)
        
        features.forEach {
            val value = it.second
            if (value != null) {
                val index = getFeatureIndex(it.first)
                featureArray[index] = value.toDouble()

                val undefIndex = getUndefinedFeatureIndex(it.first)
                featureArray[undefIndex] = 0.0
            } else {
                val index = getFeatureIndex(it.first)
                featureArray[index] = doubleFeatures[it.first]!!
            }
        }
    }

    
    
    private fun resetArray() {
        featureArray.fill(0.0)
        setFactortsUndefined()
        setDoubleFeaturesDefaultValues()
        setBinaryFeaturesDefaultValue()
    }
    

    private fun setBinaryFeaturesDefaultValue() {
        binaryFeatures.entries
                .forEach {
                    val index = featuresOrder[it.key]!!
                    val defaultValue = it.value[FeatureUtils.DEFAULT]
                    featureArray[index] = defaultValue!!
                }
    }

    
    private fun setDoubleFeaturesDefaultValues() {
        doubleFeatures.entries
                .forEach {
                    val index = featuresOrder[it.key]!!
                    val defaultValue = it.value
                    featureArray[index] = defaultValue
                }
    }

    
    private fun setFactortsUndefined() {
        featuresOrder
                .filterKeys { it.endsWith(FeatureUtils.UNDEFINED) }
                .map { it.value }
                .forEach {
                    featureArray[it] = 1.0
                }
    }

    
    fun processFeature(name: String, value: Any) {
        when {
            binaryFeatures[name] != null      -> processBinary(name, value, binaryFeatures[name]!!)
            doubleFeatures[name] != null      -> processDouble(name, value, doubleFeatures[name]!!)
            categoricalFeatures[name] != null -> processCategorical(name, value, categoricalFeatures[name]!!)
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }
    
    
    private fun processCategorical(name: String, value: Any, knownValuesSet: Set<String>) {
        if (value == FeatureUtils.UNDEFINED) {
            return
        }
        else if (knownValuesSet.contains(value)) {
            val index = getFeatureIndex("$name=$value")
            featureArray[index] = 1.0
            
            val undefIndex = getUndefinedFeatureIndex(name)
            featureArray[undefIndex] = 0.0
        }
        else {
            val index = getFeatureIndex("$name=${FeatureUtils.OTHER}")
            featureArray[index] = 1.0
        }
    }

    
    private fun processDouble(name: String, value: Any, defaultValue: Double) {
        val index = getFeatureIndex(name)
        if (value == FeatureUtils.UNDEFINED) {
            featureArray[index] = defaultValue
        }
        else {
            val doubleValue = (value as String).toDouble()
            featureArray[index] = Math.min(doubleValue, MAX_DOUBLE_VALUE)
            
            val undefIndex = getUndefinedFeatureIndex(name)
            featureArray[undefIndex] = 0.0
        }
    }

    
    private fun processBinary(name: String, value: Any, valueTransformer: Map<String, Double>) {
        val index = getFeatureIndex(name)
        val transformedValue = valueTransformer[value]

        if (value == "UNDEFINED" || transformedValue == null) {
            featureArray[index] = valueTransformer["default"]!!
        }
        else {
            featureArray[index] = transformedValue
            val undefIndex = getUndefinedFeatureIndex(name)
            featureArray[undefIndex] = 0.0
        }
    }

    
    private fun getFeatureIndex(name: String): Int {
        return featuresOrder[name]!!
    }

    
    private fun getUndefinedFeatureIndex(name: String): Int {
        val undefinedName = FeatureUtils.getUndefinedFeatureName(name)
        return featuresOrder[undefinedName]!!
    }

}


fun <T> Sequence<T>.select(predicate: (T) -> Boolean): Sequence<T> {
    return filter(predicate)
}
