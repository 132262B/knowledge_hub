package org.example.springmongodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories("org.example.springmongodb.repository")
class SpringMongodbApplication

fun main(args: Array<String>) {
    runApplication<SpringMongodbApplication>(*args)
}
