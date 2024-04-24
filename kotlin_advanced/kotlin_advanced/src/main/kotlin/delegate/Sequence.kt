package delegate

fun main () {
    val fruits = listOf(
        MyFruit("apple", 10000),
        MyFruit("apple", 9000),
        MyFruit("banana", 2000),
    )

    val avg = fruits.asSequence()
        .filter { it.name == "apple" }
        .map { it.price }
        .take(10_000)
        .average()

    println(avg)

}

data class MyFruit(
    val name: String,
    val price: Int,
)
