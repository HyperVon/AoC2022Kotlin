import Day2Part1.Result.*
import java.io.File

object Day2Part1 {
    private const val A = "A"
    private const val B = "B"
    private const val C = "C"

    const val X = "X"
    const val Y = "Y"
    const val Z = "Z"

    const val ROCK = "Rock"
    const val PAPER = "Paper"
    const val SCISSORS = "Scissors"

    private const val FILE = "src/main/resources/Day2Part1Input.txt"

    enum class Result(val value: Int) {
        Win(6),
        Draw(3),
        Loss(0);
    }

    val myMapScores = mapOf(X to 1, Y to 2, Z to 3)
    val myMapChoices = mapOf(X to ROCK, Y to PAPER, Z to SCISSORS)
    val theirMapChoices = mapOf(A to ROCK, B to PAPER, C to SCISSORS)

    @JvmStatic
    fun main(args: Array<String>) {
        println("My score would be: ${calculateScore()}")
    }

    fun calculateScore() = createPairsFromFile()
        .sumOf { (theirChoice, myChoice) -> calculateResult(myChoice, theirChoice).value + myMapScores[myChoice]!! }

    fun createPairsFromFile() =
        File(FILE)
            .readLines()
            .map { it.split(" ") }
            .map { it[0] to it[1] }

    private fun calculateResult(myChoice: String, theirChoice: String): Result {
        val mine = myMapChoices[myChoice]
        val theirs = theirMapChoices[theirChoice]

        when (mine) {
            theirs -> return Draw
            ROCK -> when (theirs) {
                PAPER -> return Loss
                SCISSORS -> return Win
            }

            PAPER -> when (theirs) {
                ROCK -> return Win
                SCISSORS -> return Loss
            }

            SCISSORS -> when (theirs) {
                ROCK -> return Loss
                PAPER -> return Win
            }
        }

        throw IllegalArgumentException("Invalid input")
    }
}