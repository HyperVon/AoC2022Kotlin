import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


object TestSuite {
    private const val DAY1_PART1_EXPECTED_TOTAL = 69281
    private const val DAY1_PART2_EXPECTED_TOTAL = 201524
    private const val DAY2_PART1_EXPECTED_SCORE = 11841
    private const val DAY2_PART2_EXPECTED_SCORE = 13022
    private const val DAY3_PART1_EXPECTED_SCORE = 7831
    private const val DAY3_PART2_EXPECTED_SCORE = 2683
    private const val DAY25_PART1_EXPECTED_VALUE = "2-20=01--0=0=0=2-120"

    @Test
    fun testDay1Part1() {
        assertEquals(DAY1_PART1_EXPECTED_TOTAL, Day1Part1.calculateTotal().value)
    }

    @Test
    fun testDay1Part2() {
        assertEquals(DAY1_PART2_EXPECTED_TOTAL, Day1Part2.calculateTotal())
    }

    @Test
    fun testDay2Part1() {
        assertEquals(DAY2_PART1_EXPECTED_SCORE, Day2Part1.calculateScore())
    }

    @Test
    fun testDay2Part2() {
        assertEquals(DAY2_PART2_EXPECTED_SCORE, Day2Part2.calculateScore())
    }

    @Test
    fun testDay3Part1() {
        assertEquals(DAY3_PART1_EXPECTED_SCORE, Day3Part1.calculateSum())
    }

    @Test
    fun testDay3Part2() {
        assertEquals(DAY3_PART2_EXPECTED_SCORE, Day3Part2.calculateSum())
    }

    @Test
    fun testDay25Part1() {
        assertEquals(DAY25_PART1_EXPECTED_VALUE, Day25Part1.convertToSnafu(Day25Part1.calculateSum()))
    }
}