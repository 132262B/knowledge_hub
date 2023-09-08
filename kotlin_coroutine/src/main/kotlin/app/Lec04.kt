package app

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(): Unit = runBlocking {
    val time = measureTimeMillis {
        val job1 = async(start = CoroutineStart.LAZY) { apiCall1() }
        val job2 = async(start = CoroutineStart.LAZY) { apiCall2() }

        job1.start()
        job2.start()
        printWithThread(job1.await() + job2.await())
    }

    printWithThread("소요 시간 : $time ms")
}
// 결과
// [main] - 3
// [main] - 소요 시간 : 1519 ms


// suspend 를 쓴 이유는 delay가 suspend라서 그럼.
suspend fun apiCall1(): Int {
    delay(1_000L)
    return 1
}

suspend fun apiCall2(): Int {
    delay(1_500L)
    return 2
}