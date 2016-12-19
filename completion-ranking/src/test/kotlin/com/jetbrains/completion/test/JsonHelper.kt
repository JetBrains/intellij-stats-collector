package com.jetbrains.completion.test

import com.google.gson.reflect.TypeToken
import com.jetbrains.completion.ranker.gson
import com.jetbrains.completion.ranker.readFile

typealias CompletionData = List<Map<String, Any>>

fun CompletionData.findWithSessionUid(sessionUid: String) = filter { it["sessionUid"] == sessionUid }

fun readJsonMap(fileName: String): CompletionData {
    val text = readFile(fileName)
    val typeToken = object : TypeToken<CompletionData>() {}
    return gson.fromJson<CompletionData>(text, typeToken.type)
}