package app

import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

suspend fun main() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(1000L)
        printWithThread("Job 1")
    }

    job.join()
}
