package ru.aasmc.io.writer_reader

import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

fun main() {
    val fName = "writer.txt"
    val fis = FileInputStream(fName)
    val reader = InputStreamReader(fis, StandardCharsets.UTF_8)
    reader.use { iReader ->
        iReader.forEachLine {
            println(it)
        }
    }
}