package app.training.controller

import app.training.usecase.ContentUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CoroutinesTestController(
    private val contentUseCase : ContentUseCase
) {

    @GetMapping("/api/test")
    suspend fun findMyContent() {
        return contentUseCase.findMyContent(1L)
    }

}