package ru.aasmc.io.buffers

import java.nio.CharBuffer

fun main() {
    val poem = arrayOf(
        "Roses are red",
        "Violets are blue",
        "Sugar is sweet",
        "And so are you."
    )

    val buffer = CharBuffer.allocate(50)
    for (i in poem.indices) {
        // fill the buffer
        for (j in 0 until poem[i].length) {
            buffer.put(poem[i].toCharArray()[j])
        }

        // flip the buffer so that its contents can be read
        buffer.flip()

        // drain the buffer
        while (buffer.hasRemaining()) {
            print(buffer.get())
        }

        // Empty the buffer to prevent BufferOverflowException.
        buffer.clear()
        println()
    }
}