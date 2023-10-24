package openapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OpenApiSwaggerSettingApplication

fun main(args: Array<String>) {
    runApplication<OpenApiSwaggerSettingApplication>(*args)
}
