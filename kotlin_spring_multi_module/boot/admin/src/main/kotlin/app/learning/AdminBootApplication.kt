package app.learning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AdminBootApplication

fun main(args: Array<String>) {
    runApplication<AdminBootApplication>(*args)
}
