import Day2Part1.PAPER
import Day2Part1.ROCK
import Day2Part1.Result
import Day2Part1.Result.*
import Day2Part1.SCISSORS
import Day2Part1.createPairsFromFile
import Day2Part1.myMapChoices
import Day2Part1.myMapScores
import Day2Part1.theirMapChoices
import java.io.File

/*
--- Part Two ---
The Elf finishes helping with the tent and sneaks back over to you.
"Anyway, the second column says how the round needs to end:
  X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"

The total score is still calculated in the same way,
but now you need to figure out what shape to choose so the round ends as indicated.
The example above now goes like this:

In the first round, your opponent will choose Rock (A), and you need the round to end in a draw (Y),
so you also choose Rock. This gives you a score of 1 + 3 = 4.

In the second round, your opponent will choose Paper (B), and you choose Rock so you lose (X) with a score of 1 + 0 = 1.
In the third round, you will defeat your opponent's Scissors with Rock for a score of 1 + 6 = 7.
Now that you're correctly decrypting the ultra top secret strategy guide, you would get a total score of 12.

Following the Elf's instructions for the second column, what would your total score be if everything goes exactly
according to your strategy guide?
 */
object Day2Part2 {
    val secondColumnMap = mapOf("X" to Loss, "Y" to Draw, "Z" to Win)
    val swappedMap = swap(myMapChoices)
    val choiceMap = mapOf(
        ROCK to mapOf(
            Draw to swappedMap[ROCK]!!,
            Loss to swappedMap[SCISSORS]!!,
            Win to swappedMap[PAPER]!!
        ),
        PAPER to mapOf(
            Draw to swappedMap[PAPER]!!,
            Loss to swappedMap[ROCK]!!,
            Win to swappedMap[SCISSORS]!!
        ),
        SCISSORS to mapOf(
            Draw to swappedMap[SCISSORS]!!,
            Loss to swappedMap[PAPER]!!,
            Win to swappedMap[ROCK]!!
        )
    )

    @JvmStatic
    fun main(args: Array<String>) {
        println("My score would be: ${calculateScore()}")
    }

    fun calculateScore() =
        createPairsFromFile()
            .sumOf { (theirChoice, myResult) ->
                myMapScores[determineChoice(
                    theirChoice,
                    secondColumnMap[myResult]!!
                )]!! + secondColumnMap[myResult]!!.value
            }

    private fun determineChoice(theirChoice: String, result: Result): String =
        choiceMap[theirMapChoices[theirChoice]]?.get(result)!!

    private fun swap(map: Map<String, String>): Map<String, String> = map.map { (key, value) -> value to key }.toMap()

}