package app.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testService: TestService,
) {

    @GetMapping("/test")
    fun test(): String = testService.test()

}