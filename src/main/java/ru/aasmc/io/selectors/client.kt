package ru.aasmc.io.selectors

import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel
import java.util.*

val buffer = ByteBuffer.allocateDirect(8)

fun main() {
    val port = DEFAULT_PORT
    try {
        val sc = SocketChannel.open()
        val addr = InetSocketAddress("localhost", port)
        sc.connect(addr)

        var time = 0L
        while (sc.read(bb) != -1) {
            bb.flip()
            while (bb.hasRemaining()) {
                time = time shl 8
                val byte = bb.get()
                val i: Int = byte.toInt() and 255
                time = time or i.toLong()
            }
            bb.clear()
        }
        println(Date(time))
        sc.close()
    } catch (ioe: IOException) {
        error("I/O error: ${ioe.message}" )
    }
}