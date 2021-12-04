fun main() {
    fun part1(input: List<String>): Int {
        val markedNumbers = input.first().split(",").map { it.toInt() }
        val boards = mutableListOf<List<List<Int>>>()
        var tmp = mutableListOf<List<Int>>()

        var currentNumberIndex = 0
        var victory = false
        var winningBoardIndex = 0
        fun checkWinningBoard() {
            boards.forEachIndexed { index, board ->
                board.forEach { line ->
                    if (markedNumbers.subList(0, currentNumberIndex+1).containsAll(line)) {
                        victory = true
                        winningBoardIndex = index
                    }
                }
                for (i in 0 until board.size-1) {
                    var numOfWinningNumbers = 0
                    board.forEach { line ->
                        if (markedNumbers.subList(0, currentNumberIndex+1).contains(line[i])) numOfWinningNumbers++
                    }
                    if (numOfWinningNumbers == board.size) {
                        victory = true
                        winningBoardIndex = index
                    }
                }
            }
        }

        fun calculateResult(): Int {
            var sum = 0
            boards[winningBoardIndex].forEach {
                it.forEach { num ->
                    if ( !(markedNumbers.subList(0, currentNumberIndex+1).contains(num)) ) {
                        sum += num
                    }
                }
            }
            return sum * markedNumbers[currentNumberIndex]
        }

        for (i in 2 until input.size) {
            val line = input[i].trim().replace("  ", " ").split(" ").map {
                try {
                    it.toInt()
                } catch (e: Exception) {
                    -1
                }
            }
            if (line.first() == -1 || input.size-1 == i) {
                if (input.size-1 == i) {
                    tmp.add(line)
                }
                boards.add(tmp)
                tmp = mutableListOf()
            } else {
                tmp.add(line)
            }
        }

        while (currentNumberIndex < markedNumbers.size-1 && !victory) {
            checkWinningBoard()
            if (!victory) {
                currentNumberIndex++
            }
        }
        return calculateResult()
    }

    fun part2(input: List<String>): Int {

        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    //check(part2(testInput) == 5)

    val input = readInput("Day04")
    println(part1(input))
    //println(part2(input))
}
