package ru.aasmc.io.channels.server.tcp

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel

fun main() {
    println("Starting server...")

    val ssc = ServerSocketChannel.open()
    ssc.socket().bind(InetSocketAddress(9999))
    ssc.configureBlocking(false)
    val msg = "Local address ${ssc.socket().localSocketAddress}"

    val buffer = ByteBuffer.wrap(msg.toByteArray())
    while (true) {
        println(".")
        val socketChannel = ssc.accept()
        if (socketChannel != null) {
            println()
            println("Received connection from ${socketChannel.remoteAddress}")
            buffer.rewind()
            socketChannel.write(buffer)
            socketChannel.close()
        } else {
            Thread.sleep(100)
        }
    }
}