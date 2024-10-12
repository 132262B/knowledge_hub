package com.example.springcloudfunction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class SpringCloudFunctionApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudFunctionApplication>(*args)
}