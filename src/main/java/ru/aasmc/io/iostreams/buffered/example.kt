package ru.aasmc.io.iostreams.buffered

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val fos = FileOutputStream("example.txt")
    val bos = BufferedOutputStream(fos)
    bos.use { os ->
        os.write(11)
    }

    val fis = FileInputStream("example.txt")
    val bis = BufferedInputStream(fis)
    bis.use {
        val ch = it.read()
        println(ch)
    }
}