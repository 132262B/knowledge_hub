package dsl

import java.time.LocalDate

class OperatorOverLoading {
}

data class Point(
    val x: Int,
    val y: Int
) {
    fun zeroPointSymmetry() : Point = Point(-x, -y)

    operator fun unaryMinus() : Point = Point(-x, -y)

    operator fun inc(): Point = Point(x + 1, y + 1)
}

fun main() {

    var point = Point(20, -10)
    println(point.zeroPointSymmetry())
    println(-point)

    println(++point)

    val original = LocalDate.of(2024, 1, 1).plusDays(3)

    val dateTime1 = LocalDate.of(2024, 1, 1).plus(Days(3))
    val dateTime2 = LocalDate.of(2024, 1, 1) + Days(3)

    println(original)
    println(dateTime1)
    println(dateTime2)

}

data class Days(val days: Long)

operator fun LocalDate.plus(days: Days): LocalDate = this.plusDays(days.days)
