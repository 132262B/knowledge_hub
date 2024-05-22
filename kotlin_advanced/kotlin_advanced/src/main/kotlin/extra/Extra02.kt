package extra

import kotlin.system.measureTimeMillis

fun main() {
    val result = measureTimeMillis {
        repeat(1000000) {
            println("Hello")
        }
    }

    println(result)
}

class Extra02 {
}
