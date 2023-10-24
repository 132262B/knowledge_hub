package openapi.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(getInfo())
            .servers(getServers())
    }

    // swagger 설명 info 작업
    private fun getInfo(): Info {
        return Info()
            .title(SWAGGER_TITLE)
            .description(SWAGGER_DESCRIPTION)
            .version(API_VERSION)
    }


    // swagger에 server 종류 추가
    private fun getServers(): List<Server> {
        return listOf(
            Server().url("http://localhost:8080").description("localhost"),
            Server().url("http://localhost:8080").description("develop"),
            Server().url("http://localhost:8080").description("test"),
            Server().url("http://localhost:8080").description("staging-a"),
            Server().url("http://localhost:8080").description("production"),
        )
    }

    companion object {
        const val SWAGGER_TITLE = "타이틀"
        const val SWAGGER_DESCRIPTION = "설명"
        const val API_VERSION = "1.0.0"

    }
}