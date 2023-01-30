package ru.aasmc.io.selectors

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel

const val DEFAULT_PORT = 9999

val bb = ByteBuffer.allocateDirect(8)

fun main() {
    val port = DEFAULT_PORT
    println("Server starting ... listening on port $port")

    val ssc = ServerSocketChannel.open()
    val ss = ssc.socket()
    ss.bind(InetSocketAddress(port))
    ssc.configureBlocking(false)

    val s = Selector.open()
    ssc.register(s, SelectionKey.OP_ACCEPT)
    while (true) {
        val n = s.select()
        if (n == 0) continue
        val it = s.selectedKeys().iterator()
        while (it.hasNext()) {
            val key = it.next() as SelectionKey
            if (key.isAcceptable) {
                val sc = (key.channel() as ServerSocketChannel).accept() ?: continue
                println("Receiving connection...")
                bb.clear()
                bb.putLong(System.currentTimeMillis())
                bb.flip()
                println("Writing current time...")
                while (bb.hasRemaining()) {
                    sc.write(bb)
                }
                sc.close()
            }
            it.remove()
        }
    }
}
