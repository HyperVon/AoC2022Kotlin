import Day4Part1.createRanges

object Day4Part2 {

    @JvmStatic
    fun main(args: Array<String>) {
        println(calculateTotal())
    }

    private fun contains(firstRange: IntRange, secondRange: IntRange) =
        firstRange.contains(secondRange.first) || firstRange.contains(secondRange.last)

    private fun overlaps(firstRange: IntRange, secondRange: IntRange) =
        contains(firstRange, secondRange) || contains(secondRange, firstRange)

    fun calculateTotal() = createRanges().count { (firstRange, secondRange) -> overlaps(firstRange, secondRange) }
}