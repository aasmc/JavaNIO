package ru.aasmc.io.serialization.two

import java.io.*

class Employee @JvmOverloads constructor(_name: String = "", _age: Int = 0) : Externalizable {
    var name = _name
    var age = _age
    override fun writeExternal(out: ObjectOutput) {
        println("writeExternal called")
        out.writeUTF(name)
        out.writeInt(age)
    }

    override fun readExternal(iss: ObjectInput) {
        println("readExternal called")
        name = iss.readUTF()
        age = iss.readInt()
    }
}

fun main() {
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
        println(obj.name)
        println(obj.age)
    }
}


























