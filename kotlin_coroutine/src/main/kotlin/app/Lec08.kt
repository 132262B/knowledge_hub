package app

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {

    val exceptionHandler = CoroutineExceptionHandler { content, throwable ->
        printWithThread("예외")
    }

    val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
        throw IllegalArgumentException()
    }

    delay(1_000)
}