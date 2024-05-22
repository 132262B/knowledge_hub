package extra

import kotlin.system.measureTimeMillis

fun main() {
    acceptOnlyTwo2(1)
}
fun acceptOnlyTwo2(num : Int) {
    check(num == 2) { "num should be 2" }
}

class Extra04 {
}

