import java.io.File

object Day3Part1 {

    const val FILE = "src/main/resources/Day3Part1Input.txt"

    fun calculatePriority(char: Char): Int =
        if (char.isLowerCase()) char.code - 96 else char.code - 64 + 26

    fun calculateSum(): Int =
        File(FILE)
            .readLines()
            .map { it.toCharArray().toList().chunked(it.length / 2) }
            .map { it.fold(it.first()) { acc, list -> acc.intersect(list.toSet()).toList() } }
            .flatten()
            .sumOf { calculatePriority(it) }


    @JvmStatic
    fun main(args: Array<String>) {
        println(calculateSum())
    }
}