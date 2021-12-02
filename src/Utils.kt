import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/puzzle-inputs", "$name.txt").readLines()

/**
 * Reads lines from the given input txt file.
 * And creates a list of Pairs of strings and ints (used in day 2)
 */
fun inputSplitStringAndInt(name: String): List<Pair<String, Int>> = readInput(name).map { readInputs ->
        val split = readInputs.split(" ")
        split[0] to split[1].toInt()
    }

/**
 * Reads lines as Ints from the given input txt file.
 */
fun readInputAsInts(name: String) = File("src/puzzle-inputs", "$name.txt").readLines().map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
