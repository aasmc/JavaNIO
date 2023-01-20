package ru.aasmc.io.randomaccess

import java.io.File
import java.io.RandomAccessFile

fun main() {
    constructors()
    useFDSyncSelectively()
}

private val userDir = System.getProperty("user.dir")
private const val fileName = "raf.txt"
private val path = "$userDir${File.separator}$fileName"

private fun constructors() {
    // r - read
    // rw - read / write
    // rdw - synchronously write data
    // rws - synchronously write data and metadata
    // rwd and rws are slower because no OS buffers are used and write is performed
    // directly to the disk
    val randomAccessFile = RandomAccessFile(path, "rw")
    println(randomAccessFile)
    randomAccessFile.fd.sync()
    randomAccessFile.close()
}

private fun useFDSyncSelectively() {
    val raf = RandomAccessFile(path, "rw") // open in mode that uses buffers of the OS
    // critical write operation
    raf.writeInt(100)
    raf.fd.sync() // flush buffers
    // non-critical write
    raf.writeInt(200)
    raf.close()
}




























