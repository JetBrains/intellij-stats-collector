package com.jetbrains.completion.ranker.features


class FeatureProvider(private val allFeatures: Features) {
    
    private fun hasAnyUnknownFeatures(lookupItemProximity: Map<String, Any>, lookupItemRelevance: Map<String, Any>): Boolean {
        return lookupItemProximity.keys.subtract(allFeatures.proximity).isNotEmpty() 
                || lookupItemRelevance.keys.subtract(allFeatures.relevance).isNotEmpty()
    }
    
    fun createFullFeaturesMap(lookupItemRelevanceObjects: Map<String, Any>): Map<String, Any> {
        val lookupItemProximity = lookupItemRelevanceObjects[FeatureUtils.PROXIMITY] as? Map<String, Any> ?: emptyMap()
        val lookupItemRelevance = lookupItemRelevanceObjects.filter { it.key != FeatureUtils.PROXIMITY }
        
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


class FeatureTransformer(private val binaryFeatures: BinaryFeatureInfo, 
                         private val doubleFeatures: DoubleFeatureInfo, 
                         private val categoricalFeatures: CategoricalFeatureInfo,
                         private val featuresOrder: Map<String, Int>) {

    private val featureArray: Array<Double> = Array(featuresOrder.size, { 0.0 })
    private val maxDoubleValue = Math.pow(10.0, 10.0)

    fun toFeatureArray(): Array<Double> {
        return emptyArray()
    }
    
    fun processFeature(name: String, value: Any) {
        when {
            binaryFeatures[name] != null -> processBinary(name, value, binaryFeatures[name]!!)
            doubleFeatures[name] != null -> processDouble(name, value, doubleFeatures[name]!!)
            categoricalFeatures[name] != null -> processCategorical(name, value, categoricalFeatures[name]!!)
            else -> throw UnsupportedOperationException()
        }
    }

    private fun processCategorical(name: String, value: Any, knownValuesSet: Set<String>) {
    }

    private fun processDouble(name: String, value: Any, defaultValue: Double) {
        val index = getFeatureIndex(name)
        if (value == FeatureUtils.UNDEFINED) {
            featureArray[index] = defaultValue
            
            val undefIndex = getUndefinedFeatureIndex(name)
            featureArray[undefIndex] = 1.0
        }
        else {
            featureArray[index] = value as Double
        }
    }

    private fun processBinary(name: String, value: Any, valueTransformer: Map<String, Double>) {
        val index = getFeatureIndex(name)
        val transformedValue = valueTransformer[value]

        if (value == "UNDEFINED" || transformedValue == null) {
            featureArray[index] == valueTransformer["default"]
            
            val undefIndex = getUndefinedFeatureIndex(name)
            featureArray[undefIndex] == 1.0
        }
        else {
            featureArray[index] = transformedValue
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



