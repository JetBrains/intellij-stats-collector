/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jetbrains.completion.ranker.features



/**
 * @param position lookup element position inside lookup
 * @param query_length length of completion prefix filter(how much symbols is typed)
 * @param result_length length of lookup element string
 */
data class LookupElementInfo(val position: Int?,
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


    private val featureArray: DoubleArray = DoubleArray(featuresOrder.size)


    fun featureArray(info: LookupElementInfo, relevanceObjects: Map<String, Any?>): DoubleArray? {
        val preparedMap = preparedMap(relevanceObjects)

        val unknownFactors: List<String> = factors.unknownFactors(preparedMap.keys)
        if (unknownFactors.isNotEmpty()) {
            return null
        }

        resetArray()
        
        preparedMap.asSequence()
                .select { !ignoredFactorsMatcher.ignore(it.key) }
                .forEach { processFeature(it.key, it.value) }
        
        processElementInfo(info)
        
        return featureArray
    }

    
    private fun processElementInfo(state: LookupElementInfo) {
        val features = listOf(
                "position" to state.position, 
                "query_length" to state.query_length, 
                "result_length" to state.result_length
        )
        
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

    
    private fun processFeature(name: String, value: Any) {
        when {
            binaryFeatures[name] != null      -> processBinary(name, value, binaryFeatures[name]!!)
            doubleFeatures[name] != null      -> processDouble(name, value, doubleFeatures[name]!!)
            categoricalFeatures[name] != null -> processCategorical(name, value, categoricalFeatures[name]!!)
            else -> {
                throw UnsupportedOperationException("Unknown feature name: $name")
            }
        }
    }
    
    
    private fun processCategorical(name: String, value: Any, knownValuesSet: Set<String>) {
        when {
            value == FeatureUtils.UNDEFINED -> return
            knownValuesSet.contains(value) -> {
                val index = getFeatureIndex("$name=$value")
                featureArray[index] = 1.0

                val undefIndex = getUndefinedFeatureIndex(name)
                featureArray[undefIndex] = 0.0
            }
            else -> {
                val index = getFeatureIndex("$name=${FeatureUtils.OTHER}")
                featureArray[index] = 1.0
            }
        }
    }

    
    private fun processDouble(name: String, value: Any, defaultValue: Double) {
        val index = getFeatureIndex(name)
        if (value == FeatureUtils.UNDEFINED) {
            featureArray[index] = defaultValue
        }
        else {
            val doubleValue = double(value)
            featureArray[index] = Math.min(doubleValue, MAX_DOUBLE_VALUE)
            
            val undefIndex = getUndefinedFeatureIndex(name)
            featureArray[undefIndex] = 0.0
        }
    }

    
    private fun processBinary(name: String, value: Any, valueTransformer: Map<String, Double>) {
        val index = getFeatureIndex(name)
        val transformedValue = valueTransformer[value.toString()]

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


private fun double(value: Any): Double {
    return when (value) {
        is Double -> value
        is Int -> value.toDouble()
        is String -> value.toDouble()
        else -> value.toString().toDouble()
    }
}


/**
 * Proximity features now came like [samePsiFile=true, openedInEditor=false], need to convert to proper map
 */
private fun String.toProximityMap(): Map<String, Any> {
    val items = replace("[", "").replace("]", "").split(",")

    return items.map {
        val (key, value) = it.trim().split("=")
        "prox_$key" to value
    }.toMap()
}


private fun preparedMap(relevance: Map<String, Any?>): Map<String, Any> {
    val proximity = "proximity"
    val proximityMap = relevance[proximity]?.toString()?.toProximityMap() ?: emptyMap()

    val resultMap = relevance.toMutableMap()
    resultMap.remove(proximity)
    resultMap.putAll(proximityMap)

    resultMap.filter { it.value == null }
            .map { it.key }
            .forEach { nullValueKey -> resultMap.remove(nullValueKey) }

    return resultMap as Map<String, Any>
}