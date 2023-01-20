package ru.aasmc.io.randomaccess

import java.io.IOException

fun main() {
    var pdb: PartsDB? = null
    try {
        pdb = PartsDB("parts.db")
        if (pdb.numRecords() == 0) {
            // Populate the database with records.

            // Populate the database with records.
            pdb.append("1-9009-3323-4x", "Wiper Blade Micro Edge", 30, 2468)
            pdb.append("1-3233-44923-7j", "Parking Brake Cable", 5, 1439)
            pdb.append("2-3399-6693-2m", "Halogen Bulb H4 55/60W", 22, 813)
            pdb.append("2-599-2029-6k", "Turbo Oil Line O-Ring ", 26, 155)
            pdb.append("3-1299-3299-9u", "Air Pump Electric", 9, 20200)
        }
        dumpRecords(pdb)
        pdb.update(1, "1-3233-44923-7j", "Parking Brake Cable", 5, 3000)
        dumpRecords(pdb)

    } catch (e: IOException) {
        System.err.println(e)
    } finally {
        pdb?.close()
    }
}

private fun dumpRecords(pdb: PartsDB) {
    val numRecords = pdb.numRecords()
    repeat(numRecords) { i ->
        val part = pdb.select(i)
        print(format(part.partNum, PartsDB.PNUMLEN, true))
        print(" | ")
        print(format(part.desc, PartsDB.DESCLEN, true))
        print(" | ")
        print(format("${part.qty}", 10, false))
        print(" | ")
        var s = "${part.ucost / 100}.${part.ucost % 100}"
        if (s[s.length - 2] == '.') {
            s += "0"
        }
        println(format(s, 10, false))
    }
    println("Number of records = ${pdb.numRecords()}")
    println()
}

private fun format(value: String, maxWidth: Int, leftAlign: Boolean): String {
    val sb = StringBuilder()
    var len = value.length
    var toWrite = value
    if (len > maxWidth) {
        len = maxWidth
        toWrite = value.substring(0, len)
    }
    if (leftAlign) {
        sb.append(toWrite)
        repeat(maxWidth - len) {
            sb.append(" ")
        }
    } else {
        repeat(maxWidth - len) {
            sb.append(" ")
        }
        sb.append(toWrite)
    }
    return sb.toString()
}