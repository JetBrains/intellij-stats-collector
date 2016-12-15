package com.jetbrains.completion.ranker.features


class FeatureProvider(private val allFeatures: Features) {
    
    private fun hasAnyUnknownFeatures(lookupItemProximity: Map<String, Any>, lookupItemRelevance: Map<String, Any>): Boolean {
        return lookupItemProximity.keys.subtract(allFeatures.proximity).isNotEmpty() 
                || lookupItemRelevance.keys.subtract(allFeatures.relevance).isNotEmpty()
    }
    
    fun createFullFeaturesMap(lookupItemRelevanceObjects: Map<String, Any>): Map<String, Any> {
        val lookupItemProximity = lookupItemRelevanceObjects[FeaturesConstants.PROXIMITY] as? Map<String, Any> ?: emptyMap()
        val lookupItemRelevance = lookupItemRelevanceObjects.filter { it.key != FeaturesConstants.PROXIMITY }
        
        if (hasAnyUnknownFeatures(lookupItemProximity, lookupItemRelevance)) return emptyMap()
        
        val undefinedProximityFeatures = allFeatures.proximity
                .subtract(lookupItemProximity.keys)
                .associateBy { FeaturesConstants.UNDEFINED }
        
        val undefinedRelevanceFeatures = allFeatures.relevance
                .subtract(lookupItemRelevance.keys)
                .associateBy { FeaturesConstants.UNDEFINED }
        
        val totalProximity = lookupItemProximity + undefinedProximityFeatures
        val totalRelevance = lookupItemRelevance + undefinedRelevanceFeatures
        
        return totalProximity + totalRelevance
    }
    
}


class FeatureTransformer(private val binaryFeatures: BinaryFeatureInfo, 
                         private val doubleFeatures: DoubleFeatureInfo, 
                         private val categoricalFeatures: CategoricalFeatureInfo) {
    
    companion object {
        private val featuresArray = Array(100, { 0.0 }) 
    }
    
    fun getArrayToDecisionTree() = featuresArray
    
    fun processFeature(name: String, value: Any) {
        when {
            binaryFeatures.containsKey(name) -> processBinary(name, value)
            doubleFeatures.containsKey(name) -> processDouble(name, value)
            categoricalFeatures.containsKey(name) -> processCategorical(name, value)
            else -> throw UnsupportedOperationException()
        }
    }

    private fun processCategorical(name: String, value: Any) {
    }

    private fun processDouble(name: String, value: Any) {
    }

    private fun processBinary(name: String, value: Any) {
        
    }

}



