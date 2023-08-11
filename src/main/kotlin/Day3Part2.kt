import Day3Part1.FILE
import Day3Part1.calculatePriority
import java.io.File

object Day3Part2 {

    fun calculateSum(): Int =
        File(FILE)
            .readLines()
            .toList()
            .chunked(3)
            .map { it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()).first() }
            .sumOf { calculatePriority(it) }

    @JvmStatic
    fun main(args: Array<String>) {
        calculateSum().also { println(it) }
    }
}