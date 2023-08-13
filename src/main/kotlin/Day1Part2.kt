import Day1Part1.createCalorieMap

object Day1Part2 {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Top 3 Elves total calorie count: ${calculateTotal()}")
    }

    fun calculateTotal(): Int {
        return createCalorieMap().values.sortedDescending().take(3).sum()
    }
}