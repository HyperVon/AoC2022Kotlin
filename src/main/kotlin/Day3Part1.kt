import java.io.File

object Day3Part1 {

    const val FILE = "src/main/resources/Day3Part1Input.txt"

    fun calculatePriority(char: Char): Int =
        if (char.isLowerCase()) char.code - 96 else char.code - 64 + 26

    fun calculateSum() =
        File(FILE)
            .readLines()
            .map {
                val charList = it.toCharArray().toList()
                val halves = charList.chunked(charList.size / 2)
                halves[0].intersect(halves[1])
            }
            .flatten()
            .sumOf { calculatePriority(it) }

    @JvmStatic
    fun main(args: Array<String>) {
        calculateSum().also { println(it) }
    }
}