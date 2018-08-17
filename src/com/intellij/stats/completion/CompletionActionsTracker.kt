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

package com.intellij.stats.completion

import com.intellij.codeInsight.lookup.LookupAdapter
import com.intellij.codeInsight.lookup.LookupEvent
import com.intellij.codeInsight.lookup.impl.LookupImpl
import com.intellij.codeInsight.lookup.impl.PrefixChangeListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.stats.experiment.WebServiceStatus

class CompletionActionsTracker(private val lookup: LookupImpl,
                               private val logger: CompletionLogger,
                               private val experimentHelper: WebServiceStatus)
    : CompletionPopupListener,
        PrefixChangeListener,
        LookupAdapter() {

    private var completionStarted = false
    private var selectedByDotTyping = false

    private val deferredLog = DeferredLog()

    private fun isCompletionActive(): Boolean {
        return completionStarted && !lookup.isLookupDisposed
                || ApplicationManager.getApplication().isUnitTestMode
    }

    override fun lookupCanceled(event: LookupEvent) {
        if (!completionStarted) return

        val timestamp = System.currentTimeMillis()
        val items = lookup.items
        val currentItem = lookup.currentItem
        if (currentItem == null) {
            deferredLog.clear()
            logger.completionCancelled(timestamp)
            return
        }

        val prefix = lookup.itemPattern(currentItem)
        val wasTyped = items.firstOrNull()?.lookupString?.equals(prefix) ?: false
        if (wasTyped || selectedByDotTyping) {
            deferredLog.log()
            logger.itemSelectedByTyping(lookup, timestamp)
        } else {
            deferredLog.clear()
            logger.completionCancelled(timestamp)
        }
    }

    override fun currentItemChanged(event: LookupEvent) {
        if (completionStarted) {
            println(event.item?.lookupString)
            return
        }

        val timestamp = System.currentTimeMillis()
        completionStarted = true
        deferredLog.defer {
            val isPerformExperiment = experimentHelper.isExperimentOnCurrentIDE()
            val experimentVersion = experimentHelper.experimentVersion()
            logger.completionStarted(lookup, isPerformExperiment, experimentVersion, timestamp) }
    }

    override fun itemSelected(event: LookupEvent) {
        if (!completionStarted) return

        val timestamp = System.currentTimeMillis()
        deferredLog.log()
        logger.itemSelectedCompletionFinished(lookup, timestamp)
    }

    override fun beforeDownPressed() {
        deferredLog.log()
    }

    override fun downPressed() {
        if (!isCompletionActive()) return

        val timestamp = System.currentTimeMillis()
        deferredLog.log()
        deferredLog.defer {
            logger.downPressed(lookup, timestamp)
        }
    }

    override fun beforeUpPressed() {
        deferredLog.log()
    }

    override fun upPressed() {
        if (!isCompletionActive()) return

        val timestamp = System.currentTimeMillis()
        deferredLog.log()
        deferredLog.defer {
            logger.upPressed(lookup, timestamp)
        }
    }

    override fun beforeBackspacePressed() {
        if (!isCompletionActive()) return
        deferredLog.log()
    }

    override fun afterBackspacePressed() {
        if (!isCompletionActive()) return

        val timestamp = System.currentTimeMillis()
        deferredLog.log()
        deferredLog.defer {
            logger.afterBackspacePressed(lookup, timestamp)
        }
    }

    override fun beforeCharTyped(c: Char) {
        if (!isCompletionActive()) return

        val timestamp = System.currentTimeMillis()
        deferredLog.log()

        if (c == '.') {
            val item = lookup.currentItem
            if (item == null) {
                logger.customMessage("Before typed $c lookup.currentItem is null; lookup size: ${lookup.items.size}", timestamp)
                return
            }
            val text = lookup.itemPattern(item)
            if (item.lookupString == text) {
                selectedByDotTyping = true
            }
        }
    }


    override fun afterAppend(c: Char) {
        if (!isCompletionActive()) return

        val timestamp = System.currentTimeMillis()
        deferredLog.log()
        deferredLog.defer {
            logger.afterCharTyped(c, lookup, timestamp)
        }
    }
}
