package com.example.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate


@SpringBootApplication
class GatewayApplication {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder()
            .rootUri("http://localhost:8080")
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}
