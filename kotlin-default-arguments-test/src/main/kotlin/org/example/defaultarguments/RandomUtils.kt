package org.example.defaultarguments

import org.example.defaultarguments.RandomUtils.generateDiscountPercent
import kotlin.random.Random

object RandomUtils {

    fun generateDiscountPercent(random: Random = Random): Int = random.nextInt(100)

}


fun main() {
    println(generateDiscountPercent (Random(10)))
    println(generateDiscountPercent (Random(10)))
    println(generateDiscountPercent ())
    println(generateDiscountPercent ())
}