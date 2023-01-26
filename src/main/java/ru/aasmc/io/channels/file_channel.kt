package ru.aasmc.io.channels

import java.io.File
import java.io.RandomAccessFile
import java.nio.ByteBuffer

fun main() {
    val file = File("temp.txt").apply {
        deleteOnExit()
    }
    val raf = RandomAccessFile(file, "rw")

    val fc = raf.channel
    fc.use { channel ->
        val pos = channel.position()
        println("Position = $pos")
        println("Size = ${channel.size()}")
        val msg = "this is a test message."
        val buffer = ByteBuffer.allocateDirect(msg.length * 2)
        buffer.asCharBuffer().put(msg)
        channel.write(buffer)
        channel.force(true)
        println("position = ${channel.position()}")
        println("size = ${channel.size()}")
        buffer.clear()
        channel.position(pos)
        channel.read(buffer)
        buffer.flip()
        while (buffer.hasRemaining()) {
            println(buffer.char)
        }
    }
}