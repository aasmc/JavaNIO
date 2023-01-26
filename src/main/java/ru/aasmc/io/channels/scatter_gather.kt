package ru.aasmc.io.channels

import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.channels.GatheringByteChannel
import java.nio.channels.ScatteringByteChannel

fun main() {
    val fis = FileInputStream("test.txt")
    // since we create a channel based on FileInputStream it is actually a
    // file channel that implements ScatteringByteChannel, so the cast is safe
    val src = Channels.newChannel(fis) as ScatteringByteChannel
    val buffer1 = ByteBuffer.allocateDirect(64)
    val buffer2 = ByteBuffer.allocateDirect(8)
    val buffers = arrayOf(buffer1, buffer2)

    src.use { source ->
        source.read(buffers)
        buffer1.flip()
        while (buffer1.hasRemaining()) {
            println(buffer1.get().toInt().toChar())
        }

    }
    println()

    while (buffer2.hasRemaining()) {
        println(buffer2.get().toInt().toChar())
    }
    buffer1.rewind()
    buffer2.rewind()

    val fos = FileOutputStream("writer.txt")
    val dst = Channels.newChannel(fos) as GatheringByteChannel
    dst.use { destination ->
        buffers[0] = buffer2
        buffers[1] = buffer1
        destination.write(buffers)
    }
}