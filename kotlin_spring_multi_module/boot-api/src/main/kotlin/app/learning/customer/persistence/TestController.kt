package app.learning.customer.persistence

import app.learning.ExampleService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val exampleService : ExampleService,
    // private val exampleEntityRepository : exampleEntityRepository,
) {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/test")
    fun test() : String {
        logger.info { "Some message!" }
        return "test"
    }

    @GetMapping("/test2")
    fun test2(@RequestParam id : Long) : String {
        logger.info { "log 뜨네" }
        return exampleService.test(id)
    }


}