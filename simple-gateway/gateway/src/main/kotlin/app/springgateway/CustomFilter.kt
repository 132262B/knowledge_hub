package app.springgateway

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomFilter : AbstractGatewayFilterFactory<CustomFilter.Config>(Config::class.java) {

    private val log = KotlinLogging.logger {}

    override fun apply(config: Config?): GatewayFilter = GatewayFilter { exchange, chain ->
        val request = exchange.request
        val response = exchange.response

        log.info { "Custom PRE filter : request id : ${request.id}" }

        chain.filter(exchange).then(Mono.fromRunnable {
            log.info { "Custom Post filter : response id : ${response.statusCode}" }
        })
    }

    class Config
}