package ru.aasmc.io.iostreams.datastreams

import java.io.*

fun main() {
    val fileName = "values.txt"
    val f = File(fileName).also {
        it.deleteOnExit()
    }

    val fos = FileOutputStream(f)
    val dos = DataOutputStream(fos)
    dos.use { doss ->
        doss.writeInt(1995)
        doss.writeUTF("Saving this String in modified UTF-8 format!")
        doss.writeFloat(1.0F)
    }

    val fis = FileInputStream(f)
    val dis = DataInputStream(fis)
    dis.use { diss ->
        println(diss.readInt())
        println(diss.readUTF())
        println(diss.readFloat())
    }
}