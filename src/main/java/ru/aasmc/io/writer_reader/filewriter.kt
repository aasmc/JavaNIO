package ru.aasmc.io.writer_reader

import java.io.FileWriter
import java.nio.charset.StandardCharsets

fun main() {
    val fName = "writer.txt"
    val writer = FileWriter(fName, StandardCharsets.UTF_8, true)
    writer.use { iWriter ->
        repeat(10) {
            iWriter.write("String ${it * 10}\n")
        }
    }
}