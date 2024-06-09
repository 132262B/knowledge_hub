package org.example.springpath

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringPathApplication

fun main(args: Array<String>) {
	runApplication<SpringPathApplication>(*args)
}
