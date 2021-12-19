import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val split = input.map { s ->
            val (first, second) = s.split(" -> ")
            "$first,$second"
        }
        var xMax = 0
        var yMax = 0
        split.forEach { line ->
            val (x1, y1, x2, y2) = line.split(",").map { it.toInt() }
            val x = if (x1 > x2) x1 else x2
            val y = if (y1 > y2) y1 else y2

            xMax = if (x > xMax) x else xMax
            yMax = if (y > yMax) y else yMax
        }

        val output = mutableListOf<MutableList<Int>>()

        for (i in 0..yMax) {
            val field = mutableListOf<Int>()
            for (j in 0..xMax) {
                field.add(0)
            }
            output.add(field)
        }

        split.forEach { line ->
//            println("\n"+line)
            val (x1, y1, x2, y2) = line.split(",").map { it.toInt() }

            var changing = true
            var step = 0
            val xDirection = y1 == y2
            val xMini = if (x1 < x2) x1 else x2
            val xMaxi = if (x1 > x2) x1 else x2
            val yMini = if (y1 < y2) y1 else y2
            val yMaxi = if (y1 > y2) y1 else y2

            val ok = x1==x2 || y1==y2

            while (changing && ok) {

                if (xDirection) {
                    output[xMini+step][y1] += 1
//                    println("${xMini+step},$y1")
                    if (xMini+step == xMaxi) changing = false

                } else {
                    output[x1][yMini+step] += 1
//                    println("${yMini+step},$y1")
                    if (yMini+step == yMaxi) changing = false
                }
                step++
            }
//            println("\n")
        }

        var count = 0
        output.forEach { line ->
//            println(line)
            line.forEach {
                if (it >= 2) count++
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val split = input.map { s ->
            val (first, second) = s.split(" -> ")
            "$first,$second"
        }
        var xMax = 0
        var yMax = 0
        split.forEach { line ->
            val (x1, y1, x2, y2) = line.split(",").map { it.toInt() }
            val x = if (x1 > x2) x1 else x2
            val y = if (y1 > y2) y1 else y2

            xMax = if (x > xMax) x else xMax
            yMax = if (y > yMax) y else yMax
        }

        val output = mutableListOf<MutableList<Int>>()

        for (i in 0..yMax) {
            val field = mutableListOf<Int>()
            for (j in 0..xMax) {
                field.add(0)
            }
            output.add(field)
        }

        split.forEach { line ->
//            println("\n"+line)
            val (x1, y1, x2, y2) = line.split(",").map { it.toInt() }

            var changing = true
            var step = 0
            val xMini = if (x1 < x2) x1 else x2
            val xMaxi = if (x1 > x2) x1 else x2
            val yMini = if (y1 < y2) y1 else y2
            val yMaxi = if (y1 > y2) y1 else y2

            val xDirection = y1 == y2
            val verticalOrHorizontal = x1==x2 || y1==y2
            val diagonal = if (!(x1==x2 && y1==y2) && !verticalOrHorizontal) {
                abs((y2 - y1) / (x2 - x1)) == 1
            } else false
            val diagRotated = diagonal && (y2 - y1) / (x2 - x1) == -1

            while (changing && (verticalOrHorizontal || diagonal)) {

                if (verticalOrHorizontal && xDirection) {
                    output[xMini+step][y1] += 1
//                    println("vert, xD ${xMini+step},$y1")
                    if (xMini+step == xMaxi) changing = false

                } else if (verticalOrHorizontal && !diagonal) {
                    output[x1][yMini+step] += 1
//                    println("vert, !xD ${yMini+step},$y1")
                    if (yMini+step == yMaxi) changing = false
                }
                if (diagonal) {
                    val y = if (!diagRotated) yMini+step else yMaxi-step
                    output[xMini+step][y] += 1
//                    println("diag ${xMini + step},$y")
                    if (xMini+step == xMaxi || if (!diagRotated) y==yMaxi else y==yMini) changing = false
                }

                step++
            }
//            println("\n")
        }

        var count = 0
        output.forEach { line ->
//            println(line)
            line.forEach {
                if (it >= 2) count++
            }
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
