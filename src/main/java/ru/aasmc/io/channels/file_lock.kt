package ru.aasmc.io.channels

import java.io.File
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.util.concurrent.ThreadLocalRandom

const val MAXQUERIES = 150000
const val MAXUPDATES = 15000

const val RECLEN = 16

val buffer = ByteBuffer.allocate(RECLEN) // 16 byte buffer

// view of the buffer that can hold 4 integers (each of 4 bytes length)
val intBuffer = buffer.asIntBuffer()

var counter = 1

fun main() {
    val writer = ThreadLocalRandom.current().nextBoolean()
    val file = File("ex.txt")

    val raf = RandomAccessFile(file, if (writer) "rw" else "r")
    val fc = raf.channel
    if (writer) {
        update(fc)
    } else {
        query(fc)
    }

}

fun query(fc: FileChannel) {
    repeat(MAXQUERIES) {
        println("acquiring a shared lock...")
        val lock = fc.lock(0, RECLEN.toLong(), true)
        try {
            buffer.clear()
            fc.read(buffer, 0)
            val a = intBuffer.get(0)
            val b = intBuffer.get(1)
            val c = intBuffer.get(2)
            val d = intBuffer.get(3)
            println("Reading: $a $b $c $d ...")
            if (a * 2 != b || a * 3 != c || a * 4 != d) {
                error("Error happened")
            }
        } finally {
            lock.release()
        }
    }
}

fun update(fc: FileChannel) {
    for (i in 0 until MAXUPDATES) {
        println("acquiring exclusive lock...")
        val lock = fc.lock(0, RECLEN.toLong(), false)
        try {
            intBuffer.clear()
            val a = counter
            val b = counter * 2
            val c = counter * 3
            val d = counter * 4
            println("Writing: $a $b $c $d ...")
            intBuffer.put(a)
            intBuffer.put(b)
            intBuffer.put(c)
            intBuffer.put(d)
            counter++
            buffer.clear()
            fc.write(buffer, 0)
        } finally {
            lock.release()
        }
    }
}