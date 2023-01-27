package ru.aasmc.io.channels.server.udp

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

const val PORT = 9999

fun main() {
    println("Server starting and listening on port $PORT for incoming requests...")

    val dcServer = DatagramChannel.open()
    dcServer.socket().bind(InetSocketAddress(PORT))

    val symbol = ByteBuffer.allocate(4)
    val payload = ByteBuffer.allocate(16)
    while (true) {
        payload.clear()
        symbol.clear()
        val socketAddress = dcServer.receive(symbol) ?: return
        println("Received request from $socketAddress")
        val stockSymbol = String(symbol.array(), 0, 4)
        println("Symbol $stockSymbol")
        if (stockSymbol.uppercase() == "MSFT") {
            payload.putFloat(0, 37.40f)
            payload.putFloat(4, 37.22f)
            payload.putFloat(8, 37.48f)
            payload.putFloat(12, 37.41f)
        } else {
            payload.putFloat(0, 0.0f)
            payload.putFloat(4, 0.0f)
            payload.putFloat(8, 0.0f)
            payload.putFloat(12, 0.0f)
        }
        dcServer.send(payload, socketAddress)
    }
}