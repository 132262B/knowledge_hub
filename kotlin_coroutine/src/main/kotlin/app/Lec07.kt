package app

import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

fun ex1(): Unit = runBlocking {

    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException()
    }

    delay(1_000)
}


fun ex2(): Unit = runBlocking {

    val job = async(SupervisorJob()) {
        throw IllegalArgumentException()
    }

    delay(1_000)
}