package ru.aasmc.io.buffers

import java.nio.Buffer
import java.nio.ByteBuffer

fun main() {
    val buffer: Buffer = ByteBuffer.allocate(7)
    println("Capacity: ${buffer.capacity()}")
    println("Limit: ${buffer.limit()}")
    println("Position: ${buffer.position()}")
    println("Remaining: ${buffer.remaining()}")

    println("Changing buffer limit to 5")
    buffer.limit(5)

    println("Capacity: ${buffer.capacity()}")
    println("Limit: ${buffer.limit()}")
    println("Position: ${buffer.position()}")
    println("Remaining: ${buffer.remaining()}")

    println("Changing buffer position to 3")
    buffer.position(3)

    println("Position: ${buffer.position()}")
    println("Remaining: ${buffer.remaining()}")
}