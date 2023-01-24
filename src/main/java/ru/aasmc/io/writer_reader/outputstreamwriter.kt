package ru.aasmc.io.writer_reader

import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

fun main() {
    val fName = "writer.txt"
    val fos = FileOutputStream(fName)
    val writer = OutputStreamWriter(fos, StandardCharsets.UTF_8)
    writer.use {
        repeat(10) {
            writer.write("String $it\n")
        }
    }
}