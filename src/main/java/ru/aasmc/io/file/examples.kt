package ru.aasmc.io.file

import java.io.*

fun main() {
    val workDir = System.getProperty("user.dir")
    traverseRoots()
    traverseDirectories(workDir)
    createModify()
    permissions()
}

private fun traverseRoots() {
    val roots = File.listRoots()
    for (root in roots) {
        println("Partition: $root")
        println("Free space: ${root.freeSpace}")
        println("Usable space: ${root.usableSpace}")
        println("Total space: ${root.totalSpace}")
        println("*********************************")
    }
}

private fun traverseDirectories(path: String) {
    val file = File(path)
    val dirsOrFiles = file.list() ?: emptyArray()
    for (dir in dirsOrFiles) {
        println("Directory or file: $dir")
    }
    val filtered = file.list() { directory, fileName ->
        fileName.contains("build")
    } ?: emptyArray()
    println("+++++++++++++++++++++++++++")
    for (s in filtered) {
        println("Filtered dir: $s")
    }
    println("+++++++++++++++++++++++++++")
    val files = file.listFiles() ?: emptyArray()
    for (f in files) {
        println("File from listFiles: $f")
    }
    println("+++++++++++++++++++++++++++")
    val filteredFiles = file.listFiles { pathName ->
        pathName.name.contains("gradlew")
    } ?: emptyArray()
    for (filteredFile in filteredFiles) {
        println("File from filtered listFiles: $filteredFile")
    }
}

private fun createModify() {
    val userDir = System.getProperty("user.dir")
    val defaultTempDir = System.getProperty("java.io.tmpdir")
    println("Default temporary file directory: $defaultTempDir")
    val toCreateName = "test.txt"
    val toBeCreatedFile = File("$userDir${File.separator}$toCreateName")
    val created = toBeCreatedFile.createNewFile()
    println("Created new file? $created")
    println("++++++++++++++++++++++++++++")
    val tmp = File.createTempFile("tempFile", ".txt", File(userDir))
    println("Created temporary file: $tmp")
    tmp.deleteOnExit()
    println("+++++++++++++++++++++")
    val dirToBe = File("$userDir${File.separator}tempDir").apply {
        deleteOnExit()
    }
    val success = dirToBe.mkdir()
    println(if (success) "Created directory: $dirToBe" else "Failed to create a directory")
    println("+++++++++++++++++++++++++++")
}

private fun permissions() {
    val f = File("test.sh")
    val executable = f.setExecutable(true, true)
    println("Trying to set executable to file: $f. Success? $executable")
    val proc = Runtime.getRuntime().exec("sh ${f.name}")
    proc.waitFor()
    val procIs = proc.inputStream
    val reader = BufferedReader(InputStreamReader(procIs))
    var line = reader.readLine()
    while (line != null) {
        println("Line from Process ${proc.pid()}: $line")
        line = reader.readLine()
    }
}





























