import java.io.File
import kotlin.math.abs
import kotlin.math.pow

object Day25Part1 {

    const val FILE = "src/main/resources/Day25Part1Input.txt"

    fun calculateSum() =
        File(FILE)
            .readLines()
            .map { convertToDecimal(it) }
            .sumOf { it }


    private fun convertToDecimal(snafuString: String): Long {
        val charArray = snafuString.toCharArray()
        return charArray.mapIndexed { index, c ->
            val power = charArray.size - (index + 1).toDouble()
            when (c) {
                '-' -> 5.0.pow(power) * -1
                '=' -> 5.0.pow(power) * -2
                '0' -> 0
                else -> c.toString().toLong() * (5.0).pow(power)
            }
        }.sumOf {
            it.toLong()
        }
    }

    private val snafuList by lazy {
        val list = mutableListOf<Pair<Long, Long>>()
        var prev = 0L
        for (i in 1..20) {
            val max = "2".repeat(i)
            val answer = convertToDecimal(max)
            list.add(Pair(prev, answer))
            prev = answer + 1
        }
        list
    }

    fun convertToSnafu(target: Long): String {
        var current = target
        val result = StringBuilder()
        val length = snafuList.indexOfFirst { it.first <= target && it.second >= target } + 1

        length.downTo(1).forEach { position ->
            val toPower = 5.0.pow(position - 1).toLong()
            val doubled = toPower * 2
            val remaining = if (position <= 1) 2 else snafuList[position - 2].second

            if (current > 0) {
                if (toPower in current..remaining) {
                    result.append('1')
                    current -= toPower
                } else if (doubled in current..remaining) {
                    result.append('2')
                    current -= doubled
                } else {
                    val diff = toPower - current
                    val doubleDiff = doubled - current
                    val absDoubleDiff = abs(doubleDiff)
                    val absDiff = abs(diff)

                    if (absDoubleDiff <= remaining && absDoubleDiff < absDiff) {
                        result.append('2')
                        current -= doubled
                    } else if (absDiff <= remaining) {
                        result.append("1")
                        current -= toPower
                    } else {
                        result.append("0")
                    }
                }
            } else if (current < 0) {
                val abs = abs(current)
                val diff = toPower - abs(current)
                val doubleDiff = doubled - abs(current)
                val absDoubleDiff = abs(doubleDiff)
                val absDiff = abs(diff)

                if (doubled <= abs || absDoubleDiff <= remaining) {
                    result.append('=')
                    current += doubled
                } else if (toPower <= abs || absDiff <= remaining) {
                    result.append('-')
                    current += toPower
                } else {
                    result.append("0")
                }
            } else {
                result.append("0")
            }
        }

        return result.toString()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(convertToSnafu(calculateSum()))
    }
}