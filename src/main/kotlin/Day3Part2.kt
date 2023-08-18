import Day3Part1.FILE
import Day3Part1.calculatePriority
import java.io.File

object Day3Part2 {
    fun calculateSum(): Int =
        File(FILE)
            .readLines()
            .chunked(3)
            .map { it.fold(it.first().toSet()) { acc, str -> acc.intersect(str.toSet()) }.first() }
            .sumOf { calculatePriority(it) }

    @JvmStatic
    fun main(args: Array<String>) {
        calculateSum().also { println(it) }
    }
}