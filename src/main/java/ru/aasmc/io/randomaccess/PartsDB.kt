package ru.aasmc.io.randomaccess

import java.io.IOException
import java.io.RandomAccessFile
import java.lang.StringBuilder

data class Part(
    val partNum: String,
    val desc: String,
    val qty: Int,
    val ucost: Int
)

class PartsDB(private val path: String) {

    val raf = RandomAccessFile(path, "rw")

    fun append(partNum: String, partDesc: String, qty: Int, ucost: Int) {
        raf.seek(raf.length())
        write(partNum, partDesc, qty, ucost)
    }

    fun close() {
        try {
            raf.close()
        } catch (e: IOException) {
            System.err.println(e)
        }
    }

    fun numRecords(): Int {
        return (raf.length() / RECLEN).toInt()
    }

    fun select(recno: Int): Part {
        if (recno < 0 || recno >= numRecords()) {
            throw IllegalArgumentException("$recno is out of range")
        }
        raf.seek(recno.toLong() * RECLEN)
        return read()
    }

    fun update(
        recno: Int,
        partNum: String,
        partDesc: String,
        qty: Int,
        ucost: Int
    ) {
        if (recno < 0 || recno >= numRecords()) {
            throw IllegalArgumentException("$recno is out of range")
        }
        raf.seek(recno.toLong() * RECLEN)
        write(partNum, partDesc, qty, ucost)
    }

    private fun read(): Part {
        val sb = StringBuilder()
        repeat(PNUMLEN) {
            sb.append(raf.readChar())
        }
        val partNum = sb.toString().trim()
        sb.setLength(0)
        repeat(DESCLEN) {
            sb.append(raf.readChar())
        }
        val partDesc = sb.toString().trim()
        val qty = raf.readInt()
        val ucost = raf.readInt()
        return Part(partNum, partDesc, qty, ucost)
    }

    private fun write(partNum: String, partDesc: String, qty: Int, ucost: Int) {
        var sb = StringBuilder(partNum)
        if (sb.length > PNUMLEN) {
            sb.setLength(PNUMLEN)
        } else if (sb.length < PNUMLEN) {
            val len = PNUMLEN - sb.length
            repeat(len) {
                sb.append(" ")
            }
        }
        raf.writeChars(sb.toString())
        sb = StringBuilder(partDesc)
        if (sb.length > DESCLEN) {
            sb.setLength(DESCLEN)
        } else if (sb.length < DESCLEN) {
            val len = DESCLEN - sb.length
            repeat(len) {
                sb.append(" ")
            }
        }
        raf.writeChars(sb.toString())
        raf.writeInt(qty)
        raf.writeInt(ucost)
    }


    companion object {
        const val PNUMLEN = 20
        const val DESCLEN = 30
        const val QUANLEN = 4
        const val COSTLEN = 4
        const val RECLEN = 2 * PNUMLEN + 2 * DESCLEN + QUANLEN + COSTLEN
    }
}