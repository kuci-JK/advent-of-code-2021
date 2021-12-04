import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/puzzle-inputs", "$name.txt").readLines()

fun readInputBinaryNumbers(name: String) = File("src/puzzle-inputs", "$name.txt").readLines().map { input ->
    input.map {
        it.digitToInt()
    }
}

/**
 * Reads lines from the given input txt file.
 * And creates a list of Pairs of strings and ints (used in day 2)
 */
fun inputSplitStringAndInt(name: String): List<Pair<String, Int>> = readInput(name).map { readInputs ->
        val (instruction, value) = readInputs.split(" ")
        instruction to value.toInt()
    }

/**
 * Reads lines as Ints from the given input txt file.
 */
fun readInputAsInts(name: String) = File("src/puzzle-inputs", "$name.txt").readLines().map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
