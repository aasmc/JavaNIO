package ru.aasmc.io.channels.server.udp

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

fun main() {
    val s = "MSFT"
    val dcClient = DatagramChannel.open()
    val symbol = ByteBuffer.wrap(s.toByteArray())
    val response = ByteBuffer.allocate(16)
    val sa = InetSocketAddress("localhost", PORT)
    dcClient.send(symbol, sa)
    println("Receiving datagram from ${dcClient.receive(response)}")

    println("Open price: ${response.getFloat(0)}")
    println("Low price: ${response.getFloat(4)}")
    println("High price: ${response.getFloat(8)}")
    println("Close price: ${response.getFloat(12)}")
}