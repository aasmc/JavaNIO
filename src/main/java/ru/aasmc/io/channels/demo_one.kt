package ru.aasmc.io.channels

import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.nio.channels.WritableByteChannel

fun main() {
    val src = Channels.newChannel(System.`in`)
    val dest = Channels.newChannel(System.out)
    src.use { source ->
        dest.use { destination ->
            try {
                copy(source, destination)
            } catch (e: IOException) {
                error("I/O error ${e.message}")
            }
        }
    }
}

fun copy(src: ReadableByteChannel, dest: WritableByteChannel) {
    val buffer = ByteBuffer.allocateDirect(2048)
    while (src.read(buffer) != -1) {
        buffer.flip()
        dest.write(buffer)
        buffer.compact()
    }
    buffer.flip()
    while (buffer.hasRemaining()) {
        dest.write(buffer)
    }
}

fun copyAlt(src: ReadableByteChannel, dest: WritableByteChannel) {
    val buffer = ByteBuffer.allocateDirect(2048)
    while (src.read(buffer) != -1) {
        buffer.flip()
        while (buffer.hasRemaining()) {
            dest.write(buffer)
        }
        buffer.clear()
    }
}