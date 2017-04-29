package com.jetbrains.completion.ranker


fun table(dataPath: String, headerPath: String): DataTable {
    val headers = file(headerPath)
            .readLines()
            .map(String::trim)
            .filter { it.isNotEmpty() }

    return table(dataPath, headers)
}


fun table(dataPath: String, headers: List<String>): DataTable {
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
    private val columnNameIndex = headers.mapIndexed { index, name -> name to index }.toMap()

    fun distinctColumns(columnName: String): Set<String> {
        val index = columnIndex(columnName)
        return rows.asSequence().map { it[index] }.toSet()
    }

    private val rows = mutableListOf<Row>()

    fun addRow(data: List<String>) {
        assert(data.size == columnNameIndex.size)

        val row = Row(data, rows.size)
        rows.add(row)
    }

    fun rows(): List<Row> = rows

    fun rows(columnName: String, columnValue: String): List<Row> {
        val index = columnIndex(columnName)
        return rows.filter { it[index] == columnValue }
    }

    private fun columnIndex(columnName: String): Int = columnNameIndex[columnName]!!

    inner class Row(private val values: List<String>, val index: Int) {
        operator fun get(columnName: String): String {
            val index = columnIndex(columnName)
            return values[index]
        }

        operator fun get(index: Int) = values[index]
    }

    fun rowsCount(): Int {
        return rows.size
    }
}