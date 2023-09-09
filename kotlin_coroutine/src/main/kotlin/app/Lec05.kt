package app

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(): Unit = runBlocking {

    val job1 = launch {
        delay(1_000)
        printWithThread("job1")
    }

    val job2 = launch {
        delay(1_000)
        printWithThread("job2")
    }

    delay(100)
    job1.cancel()

}