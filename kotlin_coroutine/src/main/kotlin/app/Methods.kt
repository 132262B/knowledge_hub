package app

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

suspend fun newRoutine() {
    val num1 = 1
    val num2 = 2
    yield()
    printWithThread("${num1 + num2}")
}

// runBlocking = 일반루틴 세계와 코루틴 세계를 연결한다.
// { } 블럭 안에가 코루틴 영역

// launch 반환값이 없는 코루틴을 만든다.

// yield() : 지금 코루틴을 중단하고 다른 코루틴이 실행되도록 한다. (스레드를 양보한다.)
fun main() : Unit = runBlocking {
    printWithThread("START")
    launch {
        newRoutine()
    }
    yield()
    printWithThread("END")
}

// 결과
// START
// END
// 3

// -Dkotlinx.coroutines.debug 옵션을 추가하면 어떤 코루틴에서 실행되는지 알 수 있음

fun printWithThread(str : Any) {
    println("[${Thread.currentThread().name}] - $str")
}