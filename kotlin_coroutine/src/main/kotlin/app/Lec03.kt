package app

import kotlinx.coroutines.*

fun example5(): Unit = runBlocking {
    val job = async {
        5 + 3
    }

    val result = job.await() // await : async의 결과를 가져오는 함수
    printWithThread(result)
}

// job1 이 끝나면 job2를 시작
fun example4(): Unit = runBlocking {
    val job1 = launch {
        delay(1_000)
        printWithThread("job 1")
    }

    job1.join()

    val job2 = launch {
        delay(1_000)
        printWithThread("job 2")
    }
}

// 코루틴 안에서 1에서 5까지 한번 출력 후, 500ms식 쉬고 출력하는걸 반복하는데
// 아래 launch 외부에서 1초 후 job을 cancel 시켜 1,2 까지만 출력 됨.
fun example3(): Unit = runBlocking {
    val job = launch {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }

    delay(1_000L)
    job.cancel()
}
// [main] - 1
// [main] - 2



// launch 옵션을 CoroutineStart.LAZY 로 두고, 아래 코드처럼 1초 이후에 job에 start를 호출해서 코루틴을 동작 시킬 수 있음.
fun example2(): Unit = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread("hello launch")
    }

    delay(1_000L)
    job.start()
}

fun example1() {
    runBlocking {
        printWithThread("START")

        launch {
            delay(2_000L) // 2초간 아래 코드가 시작되지 않는다. 일정 시간동안 코루틴으로 이동
            printWithThread("LAUNCH END")
        }

            printWithThread("END1")
    }

    printWithThread("REAL END")
}

// 실행값
// [main] - START
// [main] - END1
// [main] - LAUNCH END
// [main] - REAL END