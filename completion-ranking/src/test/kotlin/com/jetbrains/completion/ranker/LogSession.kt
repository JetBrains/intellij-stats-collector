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

package com.jetbrains.completion.ranker


class CompletionLog(events: List<CompletionLogEvent>) {
    private val sessions = events.groupBy { it.sessionUid }

    fun session(session_id: String): CompletionSession {
        val events = sessions[session_id] ?: emptyList()
        return CompletionSession(events)
    }
}


class CompletionSession(val events: List<CompletionLogEvent>) {

    val lookupItems: Map<Int, LookupItemRelevance> by lazy {
        events.flatMap { it.newCompletionListItems }
                .map { LookupItemRelevance(it) }
                .associate { it.id.toInt() to it }
    }


    val lookupPages: List<LookupPage> by lazy {
        events.map { it.intCompletionListIds }
                .filter { it.isNotEmpty() }
                .map { ids ->
                    val items = ids.mapIndexed { index, id -> PositionedItem(index, lookupItems[id]!!) }
                    LookupPage(items)
                }
    }

}


/**
 * Static picture of what is shown in the lookup
 */
class LookupPage(val lookupItems: List<PositionedItem>) {
    val size = lookupItems.size
}


class CompletionLogEvent(event: MutableMap<String, Any>) {
    init {
        //delegation will fail without key
        event.putIfAbsent("newCompletionListItems", emptyList<Map<String, Any>>())
        event.putIfAbsent("completionListIds", emptyList<Double>())
    }

    val newCompletionListItems: List<Map<String, Any>> by event
    val sessionUid: String by event
    val completionListIds: List<Double> by event

    val intCompletionListIds: List<Int>
        get() = completionListIds.map { it.toInt() }
}


class PositionedItem(val position: Int, private val item: LookupItemRelevance) {
    val relevance: Map<String, Any>
        get() = item.relevance

    val length: Int
        get() = item.length.toInt()

    @Suppress("unused")
    val id: Int
        get() = item.id.toInt()
}


class LookupItemRelevance(relevanceLog: Map<String, Any>) {
    val relevance: Map<String, Any> by relevanceLog
    val length: Double by relevanceLog
    val id: Double by relevanceLog
}