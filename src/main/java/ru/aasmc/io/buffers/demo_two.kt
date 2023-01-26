package ru.aasmc.io.buffers

import java.nio.Buffer
import java.nio.ByteBuffer

fun main() {
    constr()
    markBuffer()
}

private fun constr() {
    val buffer = ByteBuffer.allocate(10)
    describeBuffer(buffer, 1)
    val bytes = ByteArray(1024)
    val buffer2: ByteBuffer = ByteBuffer.wrap(bytes)
    describeBuffer(buffer2, 2)
    buffer2.put(10).put(20).put(30)

    for (i in 0 until buffer2.position()) {
        println(buffer2.get(i))
    }

    val buffer3: Buffer = ByteBuffer.wrap(bytes, 10, 50)
    describeBuffer(buffer3, 3)

    val buffer4 = buffer3.duplicate()
    buffer4.position(3)
    buffer4.limit(100)
    describeBuffer(buffer4, 4)

}

private fun markBuffer() {
    val buffer = ByteBuffer.allocate(7)
    buffer.put(10).put(20).put(30).put(40)
    buffer.limit(4)
    buffer.position(1).mark().position(3)
    println(buffer.get()) // 40
    println()
    buffer.reset()
    while (buffer.hasRemaining()) println(buffer.get()) // 20 30 40
}

private fun describeBuffer(buffer: Buffer, num: Int) {
    if (buffer.hasArray()) {
        println("buffer $num array: " + buffer.array())
        println("buffer $num array offset: " + buffer.arrayOffset())
    }
    println("Capacity: " + buffer.capacity())
    println("Limit: " + buffer.limit())
    println("Position: " + buffer.position())
    println("Remaining: " + buffer.remaining())
    println()
}