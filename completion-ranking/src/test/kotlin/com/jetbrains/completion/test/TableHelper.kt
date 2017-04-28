package com.jetbrains.completion.test

import com.jetbrains.completion.ranker.file


fun table(dataPath: String, headerPath: String): DataTable {
    val headers = file(headerPath)
            .readLines()
            .map(String::trim)
            .filter { it.isNotEmpty() }

    val file = file(dataPath)
    val lines = file.readLines().map(String::trim).filter { it.isNotEmpty() }

    val table = DataTable(headers)

    lines.forEach {
        val values = it.split("\t").map(String::trim).filter { it.isNotEmpty() }
        assert(values.size == headers.size, {
            "${values.size} ${headers.size}"
        })
        table.addRow(values)
    }

    return table
}


class DataTable(headers: List<String>) {
    private val columnNameToIndex = mutableMapOf<String, Int>()

    init {
        for (i in 0..headers.size - 1) {
            val name = headers[i]
            columnNameToIndex[name] = i
        }
    }

    fun distinctSessions(name: String): Set<String> {
        val index = getColumnIndex(name)
        return rows.asSequence().map { it[index] }.toSet()
    }

    private val rows = mutableListOf<Row>()

    fun addRow(data: List<String>) {
        assert(data.size == columnNameToIndex.size)
        val row = Row(data, rows.size)
        rows.add(row)
    }

    fun rows(columnName: String, columnValue: String): List<Row> {
        val index = getColumnIndex(columnName)
        return rows.filter { it[index] == columnValue }
    }

    private fun getColumnIndex(columnName: String): Int = columnNameToIndex[columnName]!!

    inner class Row(private val values: List<String>, val index: Int) {
        operator fun get(columnName: String): String {
            val index = getColumnIndex(columnName)
            return values[index]
        }

        operator fun get(index: Int) = values[index]
    }

    fun getRowsCount(): Int {
        return rows.size
    }
}