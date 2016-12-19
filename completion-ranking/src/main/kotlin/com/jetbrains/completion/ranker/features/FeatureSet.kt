package com.jetbrains.completion.ranker.features

import kotlin.reflect.memberProperties


class FeatureProvider(private val allFeatures: Features) {
    
    private fun hasAnyUnknownFeatures(lookupItemProximity: Map<String, Any>, lookupItemRelevance: Map<String, Any>): Boolean {
        return lookupItemProximity.keys.subtract(allFeatures.proximity).isNotEmpty() 
                || lookupItemRelevance.keys.subtract(allFeatures.relevance).isNotEmpty()
    }
    
    fun createFullFeaturesMap(lookupItemRelevanceObjects: Map<String, Any>): Map<String, Any> {
        val lookupItemProximity = lookupItemRelevanceObjects[FeatureUtils.PROXIMITY] as? Map<String, Any> ?: emptyMap()
        val lookupItemRelevance = lookupItemRelevanceObjects[FeatureUtils.RELEVANCE] as? Map<String, Any> ?: emptyMap()
        
        if (hasAnyUnknownFeatures(lookupItemProximity, lookupItemRelevance)) return emptyMap()
        
        val undefinedProximityFeatures = allFeatures.proximity
                .subtract(lookupItemProximity.keys)
                .associateBy { FeatureUtils.UNDEFINED }
        
        val undefinedRelevanceFeatures = allFeatures.relevance
                .subtract(lookupItemRelevance.keys)
                .associateBy { FeatureUtils.UNDEFINED }
        
        val totalProximity = lookupItemProximity + undefinedProximityFeatures
        val totalRelevance = lookupItemRelevance + undefinedRelevanceFeatures
        
        return totalProximity + totalRelevance
    }
    
}

class CompletionState(val position: Int?,
                      val query_length: Int?,
                      val cerp_length: Int?,
                      val result_length: Int?)


class FeatureTransformer(private val binaryFeatures: BinaryFeatureInfo, 
                         private val doubleFeatures: DoubleFeatureInfo, 
                         private val categoricalFeatures: CategoricalFeatureInfo,
                         private val featuresOrder: Map<String, Int>,
                         private val featuresProvider: FeatureProvider) {

    companion object {
        private val MAX_DOUBLE_VALUE = Math.pow(10.0, 10.0)
    }
    
    private val featureArray: Array<Double> = Array(featuresOrder.size, { 0.0 })

    fun toFeatureArray(state: CompletionState, lookupRelevance: Map<String, Any>): Array<Double> {
        val fullFeaturesMap = featuresProvider.createFullFeaturesMap(lookupRelevance)
        if (fullFeaturesMap.isEmpty()) return emptyArray()

        resetFeatureArray()
        
        val relevance: Map<String, Any> = lookupRelevance[FeatureUtils.RELEVANCE] as? Map<String, Any> ?: emptyMap()
        val proximity: Map<String, Any> = lookupRelevance[FeatureUtils.PROXIMITY] as? Map<String, Any> ?: emptyMap()
        
        relevance.forEach { name, value -> processFeature(name, value) }
        proximity.forEach { name, value -> processProximityFeature(name, value) }

        processCompletionState(state)
        
        return featureArray
    }

    private fun processCompletionState(state: CompletionState) {
        CompletionState::class.memberProperties.forEach {
            val propertyValue = it.get(state)
            if (propertyValue != null) {
                val index = getFeatureIndex(it.name)
                featureArray[index] = (propertyValue as Int).toDouble()

                val undefIndex = getUndefinedFeatureIndex(it.name)
                featureArray[undefIndex] = 0.0
            } else {
                val index = getFeatureIndex(it.name)
                featureArray[index] = doubleFeatures[it.name]!!
            }
        }
    }

    private fun resetFeatureArray() {
        featureArray.fill(0.0)
        featuresOrder
                .filterKeys { it.endsWith(FeatureUtils.UNDEFINED) }
                .map { it.value }
                .forEach {
                    featureArray[it] = 1.0
                }
        
        doubleFeatures.entries
                .forEach {
                    val index = featuresOrder[it.key]
                    if (index == null) {
                        println("Feature order is null for ${it.key}")
                        return@forEach
                    }
                    
                    val defaultValue = it.value
                    featureArray[index] = defaultValue
                }
        
        binaryFeatures.entries
                .forEach { 
                    val index = featuresOrder[it.key]
                    if (index == null) {
                        println("Feature order is null for ${it.key}")
                        return@forEach
                    }
                    
                    val defaultValue = it.value[FeatureUtils.DEFAULT]
                    featureArray[index] = defaultValue!!
                }
    }

    fun processFeature(name: String, value: Any) {
        when {
            binaryFeatures[name] != null      -> processBinary(name, value, binaryFeatures[name]!!)
            doubleFeatures[name] != null      -> processDouble(name, value, doubleFeatures[name]!!)
            categoricalFeatures[name] != null -> processCategorical(name, value, categoricalFeatures[name]!!)
            else                              -> throw UnsupportedOperationException()
        }
    }

    fun processProximityFeature(name: String, value: Any) {
        processFeature("prox_$name", value)
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
            featureArray[index] == valueTransformer["default"]
        }
        else {
            featureArray[index] = transformedValue
            
            val undefIndex = getUndefinedFeatureIndex(name)
            featureArray[undefIndex] == 0.0
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



