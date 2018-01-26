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




fun <T : Row> table(dataPath: String, rowFactory: RowFactory<T>): DataTable<T> {
    val file = file(dataPath)
    val lines = file.readLines().map(String::trim).filter { it.isNotEmpty() }

    val table = DataTable(rowFactory)

    lines.forEach {
        val values = it.split("\t").map(String::trim).filter { it.isNotEmpty() }
        table.addRow(values)
    }

    return table
}



interface RowFactory<out T: Row> {
    fun row(index: Int, columns: List<String>): T
}

interface Row {
    operator fun get(columnName: String): String
    operator fun get(index: Int): String
}

open class EventRow(val index: Int,
                    private val values: List<String>,
                    private val columnNameIndex: Map<String, Int>): Row {

    val sessionId: String
        get() = get("session_id")

    val userId: String
        get() = get("user_id")

    val eventId: String
        get() = get("event_id")

    override fun get(columnName: String): String {
        val index = columnNameIndex[columnName]?: throw AssertionError("Column $columnName not found")
        return get(index)
    }

    override fun get(index: Int): String {
        return values[index]
    }

}

class CleanDataRowFactory(private val columnNameIndex: Map<String, Int>): RowFactory<EventRow> {
    override fun row(index: Int, columns: List<String>) = EventRow(index, columns, columnNameIndex)
}


class DataTable<out T : Row>(private val factory: RowFactory<T>) {
    private val rows = mutableListOf<T>()

    fun addRow(columns: List<String>) {
        val row = factory.row(rows.size, columns)
        rows.add(row)
    }

    fun rows(): List<T> = rows

    fun rows(columnName: String, columnValue: String): List<T> {
        return rows.filter { it[columnName] == columnValue }
    }

    fun rowsCount(): Int {
        return rows.size
    }

}