import kotlin.math.pow

fun main() {
    fun part1(input: List<List<Int>>): Int {
        val gammaRateBinary = mutableListOf<Int>()
        val epsilonRateBinary = mutableListOf<Int>()

        for (index in 0 until input[0].size) {
            var zeroCount = 0
            var oneCount = 0

            input.forEach {
                if (it[index] == 0) zeroCount++ else oneCount++
            }

            gammaRateBinary.add(
                if (zeroCount > oneCount) 0 else 1
            )
            epsilonRateBinary.add(
                if (zeroCount > oneCount) 1 else 0
            )
        }

        gammaRateBinary.reverse()
        epsilonRateBinary.reverse()

        var gammaRate = 0
        var epsilonRate = 0

        for (i in 0 until gammaRateBinary.size) {
            gammaRate += gammaRateBinary[i] * 2.toDouble().pow(i.toDouble()).toInt()
            epsilonRate += epsilonRateBinary[i] * 2.toDouble().pow(i.toDouble()).toInt()
        }

        return gammaRate*epsilonRate
    }

    fun part2(input: List<List<Int>>): Int {
        var oxygenData = input
        var CO2Data = input

        fun removeUnimportantData(targetIndex: Int, targetValue: Int, data: List<List<Int>>): List<List<Int>> {
            val newData = data.toMutableList()
            data.forEach {
                if (it[targetIndex] != targetValue) newData.remove(it)
            }
            return newData.toList()
        }

        var index = 0
        while (oxygenData.size != 1 || CO2Data.size != 1) {
            var zeroCountOxygen = 0
            var oneCountOxygen = 0
            var zeroCountCO2 = 0
            var oneCountCO2 = 0

            oxygenData.forEach {
                if (it[index] == 0) zeroCountOxygen++ else oneCountOxygen++
            }
            CO2Data.forEach {
                if (it[index] == 0) zeroCountCO2++ else oneCountCO2++
            }

            if (oxygenData.size != 1) {
                oxygenData = if (zeroCountOxygen > oneCountOxygen) {
                    removeUnimportantData(index, 0, oxygenData)
                } else removeUnimportantData(index, 1, oxygenData)
            }

            if (CO2Data.size != 1) {
                CO2Data = if (zeroCountCO2 <= oneCountCO2) {
                    removeUnimportantData(index, 0, CO2Data)
                } else removeUnimportantData(index, 1, CO2Data)
            }


            index++
        }
        val oxygenGeneratorRatingBinary = oxygenData.first().toMutableList()
        val CO2ScrubberRatingBinary = CO2Data.first().toMutableList()

        oxygenGeneratorRatingBinary.reverse()
        CO2ScrubberRatingBinary.reverse()

        var oxygenGeneratorRating = 0
        var CO2ScrubberRating = 0

        for (i in 0 until oxygenGeneratorRatingBinary.size) {
            oxygenGeneratorRating += oxygenGeneratorRatingBinary[i] * 2.toDouble().pow(i.toDouble()).toInt()
            CO2ScrubberRating += CO2ScrubberRatingBinary[i] * 2.toDouble().pow(i.toDouble()).toInt()
        }
        return oxygenGeneratorRating*CO2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputBinaryNumbers("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInputBinaryNumbers("Day03")
    println(part1(input))
    println(part2(input))
}
