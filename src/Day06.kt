fun main() {
    fun numberOfFish(after: Int, input: String): Long {
        val data = input.split(",").map { it.toInt() }.toMutableList()

        var counter = 0
        var adding = mutableListOf<Int>()
        while (counter < after) {
            adding = mutableListOf()
            data.forEachIndexed { index, it ->
                if (it == 0) {
                    adding.add(8)
                    data[index] = 6
                } else {
                    data[index] = it-1
                }
            }
            data.addAll(adding)
            counter++
        }
        return data.size.toLong()
    }

    fun part1(input: String): Long {

        return numberOfFish(80, input)
    }

    fun part2(input: String): Long {
        return numberOfFish(256, input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test").first()
    check(part1(testInput) == 5934L)
//    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06").first()
    //println(part1(input))
    println(part2(input))
}
