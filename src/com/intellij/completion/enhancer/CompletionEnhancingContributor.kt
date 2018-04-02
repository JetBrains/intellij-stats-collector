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

package com.intellij.completion.enhancer

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResult.wrap
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionService
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupManager
import com.intellij.codeInsight.lookup.impl.LookupImpl
import com.intellij.openapi.application.ApplicationManager
import com.intellij.sorting.language
import com.intellij.stats.completion.prefixLength


/**
 * Runs all remaining contributors and then starts another completion round with max invocation count,
 * All lookup elements added would be sorted with another sorter and will appear at the bottom of completion lookup
 */
class InvocationCountEnhancingContributor : CompletionContributor() {
    companion object {
        private val MAX_INVOCATION_COUNT = 5

        var RUN_COMPLETION_AFTER_CHARS = 2
        var isEnabledInTests = false
    }

    override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
        if (ApplicationManager.getApplication().isUnitTestMode && !isEnabledInTests) return

        val lookup = LookupManager.getActiveLookup(parameters.editor) as LookupImpl? ?: return

        val addedElements = HashSet<LookupElement>()
        val completionNumberWeigher = CompletionNumberWeigher()

        val start = System.currentTimeMillis()
        result.runRemainingContributors(parameters, {
            InvokationCountOrigin.setInvocationTime(it.lookupElement, parameters.invocationCount)
            addedElements.add(it.lookupElement)
            wrap(it.lookupElement, it.prefixMatcher, it.sorter.weighBefore("templates", completionNumberWeigher))
                    ?.let { result.passResult(it) }
        })
        val end = System.currentTimeMillis()

        parameters.language()?.registerCompletionContributorsTime(end - start)

        val typedChars = lookup.prefixLength()
        if (parameters.invocationCount < MAX_INVOCATION_COUNT && typedChars > RUN_COMPLETION_AFTER_CHARS) {
            startMaxInvocationCountCompletion(parameters, result, addedElements)
        }
    }

    private fun startMaxInvocationCountCompletion(parameters: CompletionParameters,
                                                  result: CompletionResultSet,
                                                  alreadyAddedElements: Set<LookupElement>) {
        val updatedParams = parameters
                .withInvocationCount(MAX_INVOCATION_COUNT)
                .withType(parameters.completionType)

        val start = System.currentTimeMillis()
        val completionNumberWeigher = CompletionNumberWeigher()
        CompletionService.getCompletionService().getVariantsFromContributors(updatedParams, this, {
            if (it.lookupElement in alreadyAddedElements) return@getVariantsFromContributors

            val element = UnmatchableLookupElement(it.lookupElement)
            InvokationCountOrigin.setInvocationTime(element, MAX_INVOCATION_COUNT)
            wrap(element, it.prefixMatcher, it.sorter.weighBefore("templates", completionNumberWeigher))
                    ?.let { result.passResult(it) }
        })
        val end = System.currentTimeMillis()

        parameters.language()?.registerSecondCompletionContributorsTime(end - start)
    }

}