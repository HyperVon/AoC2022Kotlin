import java.io.File
import java.util.Stack

object Day5Part1 {
    private const val NUM_COLUMNS = 9
    private const val COLUMN_WIDTH = 4

    const val FILE = "src/main/resources/Day5Part1Input.txt"

    @JvmStatic
    fun main(args: Array<String>) {
        println(calculateResult())
    }

    fun calculateResult(): String {
        val startingState =
            File(FILE)
                .readLines()
                .takeWhile { it.isNotEmpty() }
                .dropLast(1)
                .map { it.toCharArray().toList() }

        val stacks = createStacks(startingState)

        val instructions =
            File(FILE)
                .readLines()
                .dropWhile { it.isNotEmpty() }
                .drop(1)

        instructions.forEach { instruction ->
            instruction.replace("move ", "").replace(" from ", ",").replace(" to ", ",").split(",").let {
                val quantity = it[0].toInt()
                val from = it[1].toInt()
                val to = it[2].toInt()
                repeat(quantity) {
                    while (stacks[from - 1].peek() == ' ') {
                        stacks[from - 1].pop()
                    }
                    stacks[to - 1].push(stacks[from - 1].pop())
                }
            }
        }

        val result = StringBuilder()

        stacks.forEach { stack -> if (stack.isEmpty()) result.append(" ") else result.append(stack.peek()) }

        return result.toString()
    }

    private fun createStacks(startingState: List<List<Char>>): List<Stack<Char>> {
        val stacks = mutableListOf<Stack<Char>>()

        repeat(NUM_COLUMNS) {
            stacks.add(Stack())
        }

        startingState.reversed().forEachIndexed { rowNumber: Int, row: List<Char> ->
            repeat(NUM_COLUMNS) { columnNumber ->
                val value =
                    row
                        .drop(columnNumber * COLUMN_WIDTH)
                        .take(COLUMN_WIDTH)
                        .joinToString("")
                        .trim()
                        .replace("[", "")
                        .replace("]", "")
                stacks[columnNumber].push(value.firstOrNull() ?: ' ')
            }
        }

        return stacks
    }
}