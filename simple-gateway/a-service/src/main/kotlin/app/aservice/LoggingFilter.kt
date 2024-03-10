package app.aservice

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.MDC
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(0)
class LoggingFilter : Filter {

    private val log = KotlinLogging.logger {}

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val headerTraceId = httpRequest.getHeader(TRACE_ID)
        log.info { headerTraceId }
        MDC.put(TRACE_ID, headerTraceId)

        chain.doFilter(request, response)
        MDC.clear()
    }

    companion object {
        private const val TRACE_ID = "trace_id"
    }

}