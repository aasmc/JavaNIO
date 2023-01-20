package ru.aasmc.io.iostreams.pipedis

import java.io.IOException
import java.io.PipedInputStream
import java.io.PipedOutputStream

const val LIMIT = 10

fun main() {
    val pos = PipedOutputStream()
    val pis = PipedInputStream(pos)

    val sender: () -> Unit = {
        try {
            repeat(LIMIT) {
                pos.write((Math.random() * 256).toInt())
            }
            println("Finish PipedOutputStream")
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                pos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    val receiver: () -> Unit = {
        try {
            var b = pis.read()
            while (b != -1) {
                println(b)
                b = pis.read()
            }
            println("Finish PipedInputStream")
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                pis.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    val senderThread = Thread(sender)
    val receiverThread = Thread(receiver)
    senderThread.start()
    receiverThread.start()
    senderThread.join()
    receiverThread.join()
}















