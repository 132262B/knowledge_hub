package app.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringRedisLockApplication

fun main(args: Array<String>) {
    runApplication<SpringRedisLockApplication>(*args)
}
