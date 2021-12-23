fun main() {
    fun numberOfFish(after: Int, input: String): Long { // NOT EFFECTIVE WOULD CRASH ON PART 2
        val data = input.split(",").map { it.toInt() }.toMutableList()

        var counter = 0
        var adding: MutableList<Int>
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
    fun numberOfFish2(after: Int, input: String): Long {
        val data = input.split(",").map { it.toInt() }.toMutableList()

        val state = mutableListOf<Long>()

        for (i in 0..8) {
            state.add(data.count { it == i }.toLong())
//            println(state[state.lastIndex])
        }

        var counter = 0
        while (counter < after) {
            val zero = state.first()
            for (index in 0 until state.lastIndex) {
                state[index] = state[index+1]
            }
            state[6] += zero // resets current fish timer to 6
            state[state.lastIndex] = zero // creates new fishes
            counter++
        }
        var sum = 0L
        for (i in 0 until state.size) {
            sum += state[i]
        }
        return sum
    }

    fun part1(input: String): Long {

        return numberOfFish2(80, input)
    }

    fun part2(input: String): Long {
        return numberOfFish2(256, input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test").first()
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06").first()
    println(part1(input))
    println(part2(input))
}
