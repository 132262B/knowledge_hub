package openapi.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.swagger.v3.core.jackson.ModelResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ModelResolverConfig {

    @Bean
    fun modelResolver(objectMapper: ObjectMapper): ModelResolver {
        return ModelResolver(objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE))
    }

}