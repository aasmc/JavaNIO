package ru.aasmc.io.writer_reader

import java.io.FileReader
import java.io.FileWriter
import java.nio.charset.StandardCharsets

fun main() {
    val fName = "writer.txt"
    val reader = FileReader(fName, StandardCharsets.UTF_8)
    reader.use { iReader ->
        iReader.forEachLine {
            println(it)
        }
    }

    val message = "Test message"
    val tempWriter = FileWriter("temp")
    tempWriter.use { w ->
        w.write(message, 0, message.length)
    }
    val buf = CharArray(message.length)
    val tempReader = FileReader("temp")
    tempReader.use { r ->
        r.read(buf, 0, message.length)
    }
    println(buf)
}