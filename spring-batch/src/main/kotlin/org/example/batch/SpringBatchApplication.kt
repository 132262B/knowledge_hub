package org.example.batch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class SpringBatchApplication

fun main(args: Array<String>) {
    val runApplication = runApplication<SpringBatchApplication>(*args)
    exitProcess(SpringApplication.exit(runApplication))
}
