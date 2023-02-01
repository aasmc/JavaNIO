package ru.aasmc.io.charsets

fun main() {
    val encodedMsg = byteArrayOf(
        0x66, 0x61, 0xc3.toByte(), 0xa7.toByte(), 0x61, 0x64, 0x65, 0x20, 0x74,
        0x6f, 0x75, 0x63, 0x68, 0xc3.toByte(), 0xa9.toByte()
    )

    val s = String(encodedMsg, Charsets.UTF_8)
    println(s)
    println()

    val bytes = s.toByteArray()
    for (byte in bytes) {
        print((byte.toInt() and 255).toString(16) + " ")
    }
    println()
}