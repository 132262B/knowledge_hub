package app.learning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CustomerBootApplication

fun main(args: Array<String>) {
    runApplication<CustomerBootApplication>(*args)
}
