package app.training.controller

import app.training.dto.Banner
import kotlinx.coroutines.*
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import kotlin.coroutines.cancellation.CancellationException


@RestController
class CoroutinesRestController {

    @GetMapping("/suspend")
    suspend fun suspendingEndpoint(): Banner {
        delay(3000)
        return Banner()
    }

    @GetMapping("/deferred")
    fun deferredEndpoint() = GlobalScope.async {
        delay(3000)
        Banner()
    }

    @GetMapping("/sequential")
    suspend fun sequential(): List<Banner> {
        val client = WebClient.create("http://localhost:8080")

        val banner1 = client
            .get()
            .uri("/suspend")
            .accept(MediaType.APPLICATION_JSON)
            .awaitExchange()
            .awaitBody<Banner>()

        val banner2 = client
            .get()
            .uri("/suspend")
            .accept(MediaType.APPLICATION_JSON)
            .awaitExchange()
            .awaitBody<Banner>()
        return listOf(banner1, banner2)
    }

    @GetMapping("/parallel")
    suspend fun parallel(): List<Banner> = coroutineScope {
        val deferredBanner1: Deferred<Banner> = async {
            val client = WebClient.create("http://localhost:8080")
            client
                .get()
                .uri("/suspend")
                .accept(MediaType.APPLICATION_JSON)
                .awaitExchange()
                .awaitBody<Banner>()
        }
        val deferredBanner2: Deferred<Banner> = async {
            val client = WebClient.create("http://localhost:8080")
            client
                .get()
                .uri("/suspend")
                .accept(MediaType.APPLICATION_JSON)
                .awaitExchange()
                .awaitBody<Banner>()
        }
        listOf(deferredBanner1.await(), deferredBanner2.await())
    }

    @GetMapping("/error")
    suspend fun error() {
        throw IllegalStateException()
    }

    @GetMapping("/cancel")
    suspend fun cancel() {
        throw CancellationException()
    }

}