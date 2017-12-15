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

package com.jetbrains.completion.ranker.features.impl

import com.jetbrains.completion.ranker.features.CompletionFactors
import com.jetbrains.completion.ranker.features.Feature
import com.jetbrains.completion.ranker.features.Transformer


/**
 * @author Vitaliy.Bibaev
 */
class FeatureTransformer(val features: Map<String, Feature>,
                         private val ignoredFeatures: Set<String>,
                         private val completionFactors: CompletionFactors,
                         arraySize: Int)
    : Transformer {
    private val array = DoubleArray(arraySize)
    override fun featureArray(relevanceObjects: Map<String, Any?>, userFactors: Map<String, Any?>): DoubleArray? {
        val preparedMap = preparedMap(relevanceObjects)
        val unknownFactors = completionFactors.unknownFactors(preparedMap.keys)
        if (unknownFactors.isNotEmpty()) { // do not allow rank if unknown factors were found
            return null
        }

        for ((name, feature) in features) {
            val value = preparedMap[name]
            if (value == null || isFeatureIgnored(name)) {
                feature.setDefaults(array)
            } else {
                feature.process(value, array)
            }
        }

        return array
    }

    private fun isFeatureIgnored(name: String): Boolean {
        val normalized = name.substringBefore('@')
        return normalized in ignoredFeatures
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
        val result = mutableMapOf<String, Any>()
        relevance.forEach { name, value -> if (name != "proximity" && value != null) result.put(name, value) }
        val proximityMap = relevance["proximity"]?.toString()?.toProximityMap() ?: emptyMap()

        result.putAll(proximityMap)

        return result
    }
}