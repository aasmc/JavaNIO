package ru.aasmc.io.iostreams.fiscopy

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val fis = FileInputStream("example.txt")
    val fos = FileOutputStream("example_copy.txt")
    fis.use { input ->
        fos.use { output ->
            var b = input.read()
            while (b != -1) {
                output.write(b)
                b = input.read()
            }
        }
    }
}