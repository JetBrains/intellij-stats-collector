/*
 * Copyright 2000-2018 JetBrains s.r.o.
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

import org.junit.Assert
import org.junit.Test

class LookupEntryDiffTest {
    private companion object {
        const val ID = 1
        const val LENGTH = 5
        val NO_RELEVANCE_INFO = LookupEntryInfo(ID, LENGTH, null)
        val NO_RELEVANCE_INFO_COPY = LookupEntryInfo(ID, LENGTH, null)
        val EMPTY_RELEVANCE_INFO = LookupEntryInfo(ID, LENGTH, emptyMap())
        val SINGLE_RELEVANCE_ITEM_INFO = LookupEntryInfo(ID, LENGTH, mapOf("factor1" to "value1"))
        val SINGLE_RELEVANCE_ITEM_CHANGED_INFO = LookupEntryInfo(ID, LENGTH, mapOf("factor1" to "value2"))
        val SINGLE_RELEVANCE_WITH_NULL_ITEM_INFO = LookupEntryInfo(ID, LENGTH, mapOf("factor1" to null))
        val TWO_RELEVANCE_INTO = LookupEntryInfo(ID, LENGTH, mapOf("factor1" to "value1", "factor2" to "value2"))
        val TWO_RELEVANCE_INTO_COPY = LookupEntryInfo(ID, LENGTH, mapOf("factor1" to "value1", "factor2" to "value2"))

        private fun LookupEntryInfo.compareWithDifferent(newInfo: LookupEntryInfo): LookupEntryDiff {
            val diff = calculateDiff(newInfo)
                    ?: error("Diff should not be null")
            Assert.assertEquals(id, diff.id)
            return diff
        }

        private fun LookupEntryInfo.compareWithEqual(newInfo: LookupEntryInfo) {
            Assert.assertNull("Diff should be null", calculateDiff(newInfo))
        }
    }

    @Test
    fun testWithTheSame() {
        NO_RELEVANCE_INFO.compareWithEqual(NO_RELEVANCE_INFO)
        TWO_RELEVANCE_INTO.calculateDiff(TWO_RELEVANCE_INTO)
    }

    @Test
    fun testNothingChanged() {
        NO_RELEVANCE_INFO.compareWithEqual(NO_RELEVANCE_INFO_COPY)
        TWO_RELEVANCE_INTO.compareWithEqual(TWO_RELEVANCE_INTO_COPY)
    }

    @Test
    fun testNothingChangedIfRelevanceEmptyOrNull() {
        EMPTY_RELEVANCE_INFO.compareWithEqual(NO_RELEVANCE_INFO)
        NO_RELEVANCE_INFO.compareWithEqual(EMPTY_RELEVANCE_INFO)
    }

    @Test
    fun testSaveId() {
        val diff = SINGLE_RELEVANCE_ITEM_INFO.compareWithDifferent(NO_RELEVANCE_INFO)
        Assert.assertEquals(diff.id, SINGLE_RELEVANCE_ITEM_INFO.id)
    }

    @Test
    fun testDetectFromNullRelevanceChange() {
        Assert.assertEquals(1, NO_RELEVANCE_INFO.compareWithDifferent(SINGLE_RELEVANCE_ITEM_INFO).added.size)
    }

    @Test
    fun testDetectAddedFactor() {
        val diff = EMPTY_RELEVANCE_INFO.compareWithDifferent(SINGLE_RELEVANCE_ITEM_INFO)
        Assert.assertEquals(0, diff.changed.size)
        Assert.assertEquals(0, diff.removed.size)
        val added = diff.added
        Assert.assertEquals(1, added.size)
        Assert.assertTrue("factor1" in added)
        Assert.assertEquals("value1", added["factor1"])
    }

    @Test
    fun testDetectRemovedFactor() {
        val diff = TWO_RELEVANCE_INTO.compareWithDifferent(NO_RELEVANCE_INFO)
        Assert.assertEquals(0, diff.added.size)
        Assert.assertEquals(0, diff.changed.size)
        val removed = diff.removed
        Assert.assertEquals(2, removed.size)
        Assert.assertTrue("factor1" in removed)
        Assert.assertTrue("factor2" in removed)
    }

    @Test
    fun testDetectChangedFactor() {
        val diff = SINGLE_RELEVANCE_ITEM_INFO.compareWithDifferent(SINGLE_RELEVANCE_ITEM_CHANGED_INFO)
        Assert.assertEquals(0, diff.added.size)
        Assert.assertEquals(0, diff.removed.size)
        val changed = diff.changed
        Assert.assertEquals(1, changed.size)
        Assert.assertEquals("value2", changed["factor1"])
    }

    @Test
    fun testDetectChangedToNullFactor() {
        val diff = SINGLE_RELEVANCE_ITEM_INFO.compareWithDifferent(SINGLE_RELEVANCE_WITH_NULL_ITEM_INFO)
        Assert.assertEquals(0, diff.added.size)
        Assert.assertEquals(0, diff.removed.size)
        val changed = diff.changed
        Assert.assertEquals(1, changed.size)
        Assert.assertTrue("factor1" in changed)
        Assert.assertNull(changed["factor1"])
    }

    @Test
    fun testDetectChangedFromNullFactor() {
        val diff = SINGLE_RELEVANCE_WITH_NULL_ITEM_INFO.compareWithDifferent(SINGLE_RELEVANCE_ITEM_INFO)
        Assert.assertEquals(0, diff.added.size)
        Assert.assertEquals(0, diff.removed.size)
        val changed = diff.changed
        Assert.assertEquals(1, changed.size)
        Assert.assertTrue("factor1" in changed)
        Assert.assertNotNull(changed["factor1"])
    }
}