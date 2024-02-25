package app.springgateway

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.GlobalConfig>(GlobalConfig::class.java) {

    private val log = KotlinLogging.logger {}

    override fun apply(config: GlobalConfig) = GatewayFilter { exchange, chain ->
//        val request = exchange.request
//        val response = exchange.response

        log.info { "Global PRE filter : request id : ${config.message}" }
        log.info { "config.preLogger : ${config.preLogger}" }

        chain.filter(exchange).then(Mono.fromRunnable {
            log.info { "config.postLogger : ${config.postLogger}" }
        })
    }

    data class GlobalConfig(
        val message: String,
        val preLogger: Boolean,
        val postLogger: Boolean,
    )
}