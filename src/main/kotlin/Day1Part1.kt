import java.io.File

object Day1Part1 {

    private const val FILE = "src/main/resources/Day1Part1Input.txt"

    @JvmStatic
    fun main(args: Array<String>) {
        calculateTotal().also {
            println("Elf carrying the most Calories is Elf ${it.key} with total calories: ${it.value}")
        }
    }

    fun calculateTotal(): Map.Entry<Int, Int> {
        return createCalorieMap().maxBy { it.value }
    }

    fun createCalorieMap(): MutableMap<Int, Int> {
        val elfCalorieMap = mutableMapOf<Int, Int>()
        var elfNumber = 1

        File(FILE)
            .readLines()
            .forEach { line ->
                val calories = line.toIntOrNull()
                if (calories == null) {
                    elfNumber++
                } else {
                    elfCalorieMap[elfNumber] = elfCalorieMap.getOrPut(elfNumber) { 0 } + calories
                }
            }

        return elfCalorieMap
    }

}