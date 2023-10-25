package openapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SwaggerUiViewSnakeCaseApplication

fun main(args: Array<String>) {
    runApplication<SwaggerUiViewSnakeCaseApplication>(*args)
}
