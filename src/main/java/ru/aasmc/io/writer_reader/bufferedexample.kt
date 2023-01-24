package ru.aasmc.io.writer_reader

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

val lines: Array<String> = arrayOf(
    "It was the best of times, it was the worst of times,",
    "it was the age of wisdom, it was the age of foolishness,",
    "it was the epoch of belief, it was the epoch of incredulity,",
    "it was the season of Light, it was the season of Darkness,",
    "it was the spring of hope, it was the winter of despair."
)

fun main() {
    val bw = BufferedWriter(FileWriter("temp"))
    bw.use { iWriter ->
        for (line in lines) {
            iWriter.write(line, 0, line.length)
            iWriter.newLine()
        }
    }

    val br = BufferedReader(FileReader("temp"))
    br.use { iReader ->
        var line = iReader.readLine()
        while (line != null) {
            println(line)
            line = iReader.readLine()
        }
    }
}