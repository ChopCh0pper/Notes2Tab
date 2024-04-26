package com.example.notes2tab.parser

import android.content.Context
class StringTabParser(private val context: Context) {


    fun parseFile(filePath: String): Array<Array<String>> {
        val inputStream = context.resources.openRawResource(context.resources.getIdentifier(filePath, "raw", context.packageName))
        val reader = inputStream.bufferedReader()
        val lines = mutableListOf<String>()

        // Чтение строк из файла
        var line: String? = reader.readLine()
        while (line != null) {
            lines.add(line)
            line = reader.readLine()
        }

        reader.close()

        // Создание двумерного массива, заполненного пробелами
        val table = Array(lines.size) { Array(lines[0].length) { " " } }

        // Заполнение массива значениями из файла
        for (i in lines.indices) {
            for (j in lines[i].indices) {
                table[i][j] = lines[i][j].toString()
            }
        }

        return table
    }
}