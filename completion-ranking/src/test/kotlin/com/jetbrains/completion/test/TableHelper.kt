package com.jetbrains.completion.test

import com.jetbrains.completion.ranker.file


fun table(fileName: String): DataTable {
    val file = file(fileName)
    val lines = file.readLines()

    val labels = lines.first()
            .split("\t")
            .map(String::trim)

    val table = DataTable(labels)

    lines.drop(1).forEach {
        val values = it.split("\t").map(String::trim)
        table.addRow(values)
    }

    return table
}


class DataTable(labels: List<String>) {
    private val columnNameToIndex = mutableMapOf<String, Int>()

    init {
        for (i in 0..labels.size - 1) {
            val name = labels[i]
            columnNameToIndex[name] = i
        }
    }

    fun distinctSessions(name: String): Set<String> {
        val index = getColumnIndex(name)
        return rows.asSequence().map { it.valueAt(index) }.toSet()
    }

    private val rows = mutableListOf<Row>()

    fun addRow(data: List<String>) {
        assert(data.size == columnNameToIndex.size)
        val row = Row(data, rows.size)
        rows.add(row)
    }

    fun rows(columnName: String, columnValue: String): List<Row> {
        val index = getColumnIndex(columnName)
        return rows.filter { it.valueAt(index) == columnValue }
    }

    private fun getColumnIndex(columnName: String) = columnNameToIndex[columnName]!!

    inner class Row(private val values: List<String>, val index: Int) {
        fun valueOf(columnName: String): String {
            val index = getColumnIndex(columnName)
            return values[index]
        }

        fun valueAt(index: Int) = values[index]
    }

    fun getRowsCount(): Int {
        return rows.size
    }
}