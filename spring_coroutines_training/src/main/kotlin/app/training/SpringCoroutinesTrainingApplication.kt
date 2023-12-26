package app.training

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCoroutinesTrainingApplication

fun main(args: Array<String>) {
    runApplication<SpringCoroutinesTrainingApplication>(*args)
}
