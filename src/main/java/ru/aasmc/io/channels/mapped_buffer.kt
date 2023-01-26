package ru.aasmc.io.channels

import java.io.RandomAccessFile
import java.nio.channels.FileChannel

fun main() {
    val raf = RandomAccessFile("poem.txt", "rw")
    val fc = raf.channel
    val size = fc.size()
    println("Size: $size")
    fc.use { channel ->
        // read/write mapping of the underlying file
        val mbb = channel.map(FileChannel.MapMode.READ_WRITE, 0, size)
        while (mbb.remaining() > 0) {
            print(mbb.get().toInt().toChar())
        }
        println()
        println()

        for (i in 0 until mbb.limit() / 2) {
            val b1 = mbb.get(i)
            val b2 = mbb.get(mbb.limit() - i - 1)
            mbb.put(i, b2)
            mbb.put(mbb.limit() - i - 1, b1)
        }
        mbb.flip()
        while (mbb.remaining() > 1) {
            print(mbb.get().toInt().toChar())
        }
    }
}