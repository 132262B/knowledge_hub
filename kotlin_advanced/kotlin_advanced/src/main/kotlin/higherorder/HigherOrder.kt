package higherorder

class HigherOrder {
}

fun main() {
    // 람다식
    compute(5, 2) { a, b -> a + b }

    // 익명함수
    compute(5, 2, fun(a: Int, b: Int): Int {
        return a + b
    })

    // 익명함수 축약
    compute(5, 2, fun(a: Int, b: Int): Int = a + b)

    // 익명함수 더 축약
    compute(5, 2, fun(a, b) = a + b)
}

fun compute(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}
