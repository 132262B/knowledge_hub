package org.example.springpath

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/api/path/123")
    fun path2() = "고정된 path"

    @GetMapping("/api/path/{id}")
    fun path1(@PathVariable id: Long) = "path $id"

}