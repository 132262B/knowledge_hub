package generic


fun main() {

    val numbers = listOf(1, 2f, 3.0, 4, 5)
    println(numbers.filterIsInstance<Float>())

}

inline fun <reified T> List<*>.hasAnyInstanceOf() : Boolean {
    return this.any { it is T }
}

class TypeErase {


}