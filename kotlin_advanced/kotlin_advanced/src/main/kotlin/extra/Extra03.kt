package extra

import kotlin.system.measureTimeMillis

fun main() {
    acceptOnlyTwo(1)
}
fun acceptOnlyTwo(num : Int) {
    require(num == 2) { "num should be 2" }
}

class Extra03 {
}

