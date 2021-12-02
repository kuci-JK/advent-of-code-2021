fun main() {
    fun part1(input: List<Int>): Int {
        var sum = 0
        for (i in 1 until input.size) {
            if (input[i] > input[i-1]) sum++
        }
        return sum
    }

    fun part2(input: List<Int>): Int {
        var sum = 0
        for (i in 3 until input.size) {
            val first = input[i-3] + input[i-2] + input[i-1]
            val second = input[i-2] + input[i-1] + input[i]
            if (second > first) sum++
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInts("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputAsInts("Day01")
    println(part1(input))
    println(part2(input))
}
