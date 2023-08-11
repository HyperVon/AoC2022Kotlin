import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TestSuite {

    @Test
    fun testDay1Part1() {
        assertEquals(69281, Day1Part1.calculateTotal().value)
    }

    @Test
    fun testDay1Part2() {
        assertEquals(201524, Day1Part2.calculateTotal())
    }

    @Test
    fun testDay2Part1() {
        assertEquals(11841, Day2Part1.calculateScore())
    }

    @Test
    fun testDay2Part2() {
        assertEquals(13022, Day2Part2.calculateScore())
    }
}