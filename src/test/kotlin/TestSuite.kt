import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


object TestSuite {
    private const val DAY1_PART1_EXPECTED_TOTAL = 69281
    private const val DAY1_PART2_EXPECTED_TOTAL = 201524
    private const val DAY2_PART1_EXPECTED_SCORE = 11841
    private const val DAY2_PART2_EXPECTED_SCORE = 13022

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
}