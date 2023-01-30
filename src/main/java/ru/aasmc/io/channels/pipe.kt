package ru.aasmc.io.channels

import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.Pipe
import java.nio.channels.ReadableByteChannel
import java.nio.channels.WritableByteChannel

const val BUFSIZE = 10
const val LIMIT = 3

fun main() {
    val pipe = Pipe.open()
    val senderTask = {
        val src: WritableByteChannel = pipe.sink()
        val buffer = ByteBuffer.allocate(BUFSIZE)
        for (i in 0 until LIMIT) {
            buffer.clear()
            for (j in 0 until BUFSIZE) {
                buffer.put((Math.random() * 256).toInt().toByte())
            }
            buffer.flip()
            try {
                while (src.write(buffer) > 0) {

                }
            } catch (ioe: IOException) {
                ioe.printStackTrace()
            }
        }
        try {
            src.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }

    val receiverTask = {
        val source: ReadableByteChannel = pipe.source()
        val buffer = ByteBuffer.allocate(BUFSIZE)
        while (source.read(buffer) > 0) {
            buffer.flip()
            while (buffer.remaining() > 0) {
                println(buffer.get().toInt() and 255)
            }
            buffer.clear()
        }
    }
    val sender = Thread(senderTask)
    val receiver = Thread(receiverTask)
    sender.start()
    receiver.start()
}



























