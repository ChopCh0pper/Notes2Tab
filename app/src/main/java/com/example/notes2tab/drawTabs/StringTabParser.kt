package com.example.notes2tab.drawTabs

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class StringTabParser(private val context: Context) {

    fun parseFile(filePath: String): List<String> {
        val inputStream = context.resources.openRawResource(context.resources.getIdentifier(filePath, "raw", context.packageName))
        val reader = BufferedReader(InputStreamReader(inputStream))
        val lines = mutableListOf<String>()

        // Чтение строк из файла
        var line: String? = reader.readLine()
        while (line != null) {
            lines.add(line)
            line = reader.readLine()
        }

        reader.close()
        return lines
    }
}