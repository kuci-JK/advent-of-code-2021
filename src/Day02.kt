fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        var horizontal = 0
        var depth = 0

        input.forEach {
            when (it.first) {
                "forward" -> horizontal += it.second
                "up" -> depth -= it.second
                "down" -> depth += it.second
                else -> throw Exception("part1 error")
            }
        }

        return horizontal*depth
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        input.forEach {
            when (it.first) {
                "forward" -> {
                    horizontal += it.second
                    depth += (aim * it.second)
                }
                "up" -> aim -= it.second
                "down" -> aim += it.second
                else -> throw Exception("part1 error")
            }
        }

        return horizontal*depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = inputSplitStringAndInt("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = inputSplitStringAndInt("Day02")
    println(part1(input))
    println(part2(input))
}
