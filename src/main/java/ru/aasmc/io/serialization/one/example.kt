package ru.aasmc.io.serialization.one

import java.io.*

class Employee(val name: String, val age: Int) : Serializable

fun main() {
    println(Employee::class.qualifiedName)
    val fileName = "employee.txt"
    val fos = FileOutputStream(fileName)
    val oos = ObjectOutputStream(fos)
    oos.use { os ->
        val emp = Employee("John Doe", 36)
        os.writeObject(emp)
    }
    val fis = FileInputStream(fileName)
    val ois = ObjectInputStream(fis)
    ois.use { oiss ->
        val obj = oiss.readObject() as Employee
        println(obj.age)
        println(obj.name)
    }
}