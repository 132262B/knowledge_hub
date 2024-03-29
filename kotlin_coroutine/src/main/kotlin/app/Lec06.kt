package app

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {

    val job = launch(Dispatchers.Default) {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (isActive && i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread("${i ++}번째 출력!")
                nextPrintTime += 1_000L
            }

//            if (! isActive) {
//                throw CancellationException()
//            }
        }

    }
    delay(1000L)
    job.cancel()
}