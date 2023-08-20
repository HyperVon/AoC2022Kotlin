import java.io.File
import java.util.Stack

object Day5Part1 {
    private const val NUM_COLUMNS = 9
    private const val COLUMN_WIDTH = 4

    const val FILE = "src/main/resources/Day5Part1Input.txt"

    data class Instruction(val quantity: Int, val from: Int, val to: Int) {
        private fun move(stacks: List<Stack<Char>>) = stacks[to].push(stacks[from].pop())

        private fun skipEmpties(stacks: List<Stack<Char>>) {
            while (stacks[from].peek() == ' ') stacks[from].pop()
        }

        fun execute(stacks: List<Stack<Char>>) =
            repeat(quantity) {
                skipEmpties(stacks)
                move(stacks)
            }
    }

    private fun processRow(stacks: MutableList<Stack<Char>>, row: List<Char>) =
        repeat(NUM_COLUMNS) { columnNumber ->
            stacks[columnNumber].push(
                row
                    .drop(columnNumber * COLUMN_WIDTH)
                    .take(COLUMN_WIDTH)
                    .joinToString("")
                    .trim()
                    .replace("[", "")
                    .replace("]", "")
                    .firstOrNull() ?: ' '
            )
        }

    private fun createStacks(): List<Stack<Char>> =
        mutableListOf<Stack<Char>>()
            .apply { repeat(NUM_COLUMNS) { add(Stack()) } }
            .also { stacks ->
                File(FILE)
                    .readLines()
                    .takeWhile { it.isNotEmpty() }
                    .dropLast(1)
                    .map { it.toCharArray().toList() }
                    .reversed()
                    .forEach { processRow(stacks, it) }
            }

    private fun processInstructions(stacks: List<Stack<Char>>): List<Stack<Char>> =
        File(FILE)
            .readLines()
            .dropWhile { it.isNotEmpty() }
            .drop(1)
            .forEach { instructionStr ->
                instructionStr
                    .replace("move ", "")
                    .replace(" from ", ",")
                    .replace(" to ", ",")
                    .split(",")
                    .let {
                        Instruction(
                            quantity = it[0].toInt(),
                            from = it[1].toInt() - 1,
                            to = it[2].toInt() - 1
                        ).execute(stacks)
                    }
            }.let { stacks }

    private fun getTopOfStack(stack: Stack<Char>) = if (stack.isEmpty()) " " else stack.peek()

    fun calculateResult(): String = processInstructions(createStacks()).map(::getTopOfStack).joinToString("")

    @JvmStatic
    fun main(args: Array<String>) = println(calculateResult())
}