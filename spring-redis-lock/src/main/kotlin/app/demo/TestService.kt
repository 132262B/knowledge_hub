package app.demo

import app.demo.redisson.DistributedLock
import org.springframework.stereotype.Service

@Service
class TestService {

    @DistributedLock(key = "'lock-lock'", leaseTime = 10L)
    fun test() : String{
        println("테스트 시작")

        Thread.sleep(7000)

        println("테스트 종료")
        return "OK"
    }

}