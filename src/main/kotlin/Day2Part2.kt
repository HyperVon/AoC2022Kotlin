import Day2Part1.PAPER
import Day2Part1.ROCK
import Day2Part1.Result
import Day2Part1.Result.*
import Day2Part1.SCISSORS
import Day2Part1.X
import Day2Part1.Y
import Day2Part1.Z
import Day2Part1.createPairsFromFile
import Day2Part1.myMapChoices
import Day2Part1.myMapScores
import Day2Part1.theirMapChoices

object Day2Part2 {
    private val resultMap = mapOf(X to Loss, Y to Draw, Z to Win)
    private val swappedMap = swap(myMapChoices)
    private val choiceMap = mapOf(
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
                myMapScores[determineChoice(theirChoice, resultMap[myResult]!!)]!! + resultMap[myResult]!!.value
            }

    private fun determineChoice(theirChoice: String, result: Result): String =
        choiceMap[theirMapChoices[theirChoice]]?.get(result)!!

    private fun swap(map: Map<String, String>): Map<String, String> = map.map { (key, value) -> value to key }.toMap()

}