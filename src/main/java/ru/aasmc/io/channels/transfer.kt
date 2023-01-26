package ru.aasmc.io.channels

import java.io.FileInputStream
import java.nio.channels.Channels

fun main() {
    val fis = FileInputStream("poem.txt")
    fis.use { fileInput ->
        val inChannel = fileInput.channel
        val outChannel = Channels.newChannel(System.out)
        inChannel.transferTo(0, inChannel.size(), outChannel)
    }
}