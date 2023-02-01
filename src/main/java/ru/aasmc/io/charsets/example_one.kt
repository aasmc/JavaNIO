package ru.aasmc.io.charsets

import java.nio.charset.Charset

fun main() {
    val msg = "façade touché"
    val csNames = arrayOf(
        "US-ASCII",
        "ISO-8859-1",
        "UTF-8",
        "UTF-16BE",
        "UTF-16LE",
        "UTF-16"
    )
    encode(msg, Charset.defaultCharset())
    csNames.forEach { cs ->
        encode(msg, Charset.forName(cs))
    }
}

private fun encode(msg: String, cs: Charset) {
    println("Charset: $cs")
    println("Message: $msg")

    val buffer = cs.encode(msg)
    println("Encoded: ")
    var idx = 0
    while (buffer.hasRemaining()) {
        val byte = buffer.get().toInt() and 255
        var ch = byte.toChar()
        if (Character.isWhitespace(ch) || Character.isISOControl(ch)) {
            ch = '\u0000'
        }
        println("$idx: $byte $ch")
        ++idx
    }
}