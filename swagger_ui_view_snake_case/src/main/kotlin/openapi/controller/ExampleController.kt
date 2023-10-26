package openapi.controller

import openapi.dto.TestDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController {

    @GetMapping("/test1")
    fun test1(): TestDto {
        return TestDto(
            pid = 1,
            customerId = 2,
            productId = 3,
            isTest = true,
        )
    }

}