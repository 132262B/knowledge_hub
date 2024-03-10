package app.springgateway

import io.github.oshai.kotlinlogging.KotlinLogging
import org.slf4j.MDC
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

@Component
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.LoggingConfig>(LoggingConfig::class.java) {

    private val log = KotlinLogging.logger {}

    override fun apply(config: LoggingConfig): GatewayFilter {
        val filter = GatewayFilter { exchange, chain ->
            val uuid = UUID.randomUUID().toString()
            MDC.put("uuid", uuid)

            val request = exchange.request
            val response = exchange.response

            log.info { "Custom PRE filter: request id: ${request.id}, uuid: $uuid" }

            chain.filter(exchange).then(Mono.fromRunnable {
                log.info { "Custom Post filter: response id: ${response.statusCode}, uuid: $uuid" }
                MDC.remove("uuid")
            })
        }

        return OrderedGatewayFilter(filter, Ordered.HIGHEST_PRECEDENCE)
    }

    class LoggingConfig
}