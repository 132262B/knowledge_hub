package app.aservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AServiceApplication

fun main(args: Array<String>) {
    runApplication<AServiceApplication>(*args)
}
