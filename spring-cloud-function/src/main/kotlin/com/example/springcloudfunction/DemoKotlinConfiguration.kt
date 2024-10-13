package com.example.springcloudfunction

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DemoKotlinConfiguration {

    @Bean
    fun uppercase(): (String) -> String {
        println("aaa")
        return { it.toUpperCase() }
    }

    @Bean
    fun lowercase(): (String) -> String {
        return { it.toLowerCase() }
    }
}