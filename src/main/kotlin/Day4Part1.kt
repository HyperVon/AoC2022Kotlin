import java.io.File

object Day4Part1 {

    const val FILE = "src/main/resources/Day4Part1Input.txt"

    @JvmStatic
    fun main(args: Array<String>) {
        calculateTotal().also { println(it) }
    }

    fun createRanges() =
        File(FILE)
            .readLines()
            .map { line ->
                val pairs = line.split(",")
                val first = pairs[0].split("-")
                val second = pairs[1].split("-")
                Pair(
                    IntRange(first[0].toInt(), first[1].toInt()),
                    IntRange(second[0].toInt(), second[1].toInt())
                )
            }

    private fun completelyContained(firstRange: IntRange, secondRange: IntRange) =
        firstRange.contains(secondRange.first) && firstRange.contains(secondRange.last)

    fun calculateTotal(): Int =
        createRanges().count { (firstRange, secondRange) ->
            completelyContained(firstRange, secondRange) || completelyContained(secondRange, firstRange)
        }
}