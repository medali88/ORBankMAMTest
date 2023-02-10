package com.mma.orbankmamtest

import java.io.File
import java.io.FileNotFoundException

fun String.toMockJsonSource(): String {
    val inputStream = try {
        File("src/debug/assets/mock/$this").inputStream()
    } catch (e: FileNotFoundException) {
        null
    } ?: try {
        File("src/test/resources/$this").inputStream()
    } catch (e: FileNotFoundException) {
        null
    } ?: throw FileNotFoundException("\"$this\": no such file")

    return inputStream.bufferedReader().use { it.readText() }
}
