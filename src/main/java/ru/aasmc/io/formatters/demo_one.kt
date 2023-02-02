package ru.aasmc.io.formatters

import java.util.*

fun main() {
    val formatter = Formatter()
    formatter.format("%d%n", 123)
    println(formatter)

    formatter.format("%x%n", 123)
    println(formatter)

    formatter.format("%c%n", 'X')
    println(formatter)

    formatter.format("%f%n", 0.1)
    println(formatter)

    formatter.format("%s%n", "Hello, World!")
    println(formatter)

    formatter.format("%10.2f%n", 98.375)
    println(formatter)

    formatter.format("%05d%n", 123)
    println(formatter)

    formatter.format("%1\$d %1\$d%n", 123)
    println(formatter)

    formatter.format("%d %d%n", 123, 1234)
    println(formatter)
    formatter.close()

    println("System.out.printf() ++++++++++++++++++++++")
    System.out.printf("%04X%n", 478)

    System.out.printf("Current date: %1\$tb %1\$te, %1\$tY%n", System.currentTimeMillis())
}





















