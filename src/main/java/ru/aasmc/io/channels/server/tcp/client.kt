package ru.aasmc.io.channels.server.tcp

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

fun main() {
    val sc = SocketChannel.open()
    sc.configureBlocking(false)
    val addr = InetSocketAddress("localhost", 9999)
    sc.connect(addr)

    while (!sc.finishConnect()) {
        println("Waiting to finish connection")
    }

    val buffer = ByteBuffer.allocate(200)
    while (sc.read(buffer) >= 0) {
        buffer.flip()
        while (buffer.hasRemaining()) {
            print(buffer.get().toInt().toChar())
        }
        buffer.clear()
    }
    sc.close()
}