package com.intellij.stats.completion

import com.intellij.codeInsight.completion.CompletionProgressIndicator
import com.intellij.codeInsight.completion.CompletionService
import com.intellij.codeInsight.lookup.Lookup
import com.intellij.codeInsight.lookup.LookupAdapter
import com.intellij.codeInsight.lookup.LookupEvent
import com.intellij.stats.personalization.UserFactorDescriptions
import com.intellij.stats.personalization.UserFactorStorage

/**
 * @author Vitaliy.Bibaev
 */
class LookupStartedTracker : LookupAdapter() {
    override fun currentItemChanged(event: LookupEvent?) {
        val lookup = event?.lookup ?: return
        if (processLookupStarted(lookup)) lookup.removeLookupListener(this)
    }

    private fun processLookupStarted(lookup: Lookup): Boolean {
        // todo[bibaev]: avoid usage of deprecated methods
        @Suppress("DEPRECATION")
        val completionType =
                (CompletionService.getCompletionService().currentCompletion as? CompletionProgressIndicator)
                        ?.parameters?.completionType ?: return false
        UserFactorStorage.applyOnBoth(lookup.project, UserFactorDescriptions.COMPLETION_TYPE) {
            it.fireCompletionPerformed(completionType)
        }

        return true
    }
}