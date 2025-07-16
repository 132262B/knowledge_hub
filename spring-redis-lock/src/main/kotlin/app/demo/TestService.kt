package app.demo

import app.demo.redisson.DistributedLock
import org.springframework.stereotype.Service

@Service
class TestService {

    @DistributedLock(key = "#lock-lock")
    fun test() {
        println("테스트 시작")

        Thread.sleep(3000)

        println("테스트 종료")
    }

}