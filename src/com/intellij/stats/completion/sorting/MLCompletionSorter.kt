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
package com.intellij.stats.completion.sorting

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.components.ServiceManager
import com.jetbrains.completion.ranker.CompletionRanker
import com.jetbrains.completion.ranker.features.*


class MLRanker : ApplicationComponent.Adapter() {

    private lateinit var featureTransformer: FeatureTransformer
    private lateinit var ranker: CompletionRanker
    
    override fun initComponent() {
        val binary = readBinaryFeaturesInfo()
        val double = readDoubleFeaturesInfo()
        val categorical = readCategoricalFeaturesInfo()
        val allFeatures = readAllFeatures()
        val order = readFeaturesOrder()
        val featureProvider = FeatureProvider(allFeatures)
        
        featureTransformer = FeatureTransformer(binary, double, categorical, order, featureProvider)
        ranker = CompletionRanker()
    }
    
    fun rank(state: CompletionState, lookupRelevance: Map<String, Any>): Double {
        val featureArray = featureTransformer.toFeatureArray(state, lookupRelevance)
        if (featureArray != null) {
            return ranker.rank(featureArray)  
        }
        throw IllegalStateException("No feature array created $lookupRelevance")
    }
    
    companion object {
        fun getInstance(): MLRanker = ApplicationManager.getApplication().getComponent(MLRanker::class.java)
    }
}