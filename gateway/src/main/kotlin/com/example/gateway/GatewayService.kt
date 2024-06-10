package com.example.gateway

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@Service
class GatewayService(val restTemplate: RestTemplate) {

    fun createOrder(bookName: String): CompletionStage<String> {
        return CompletableFuture.supplyAsync {
            val response = restTemplate.postForEntity("http://localhost:8080/order/create/" + bookName, String(), Void::class.java)
//            Thread.sleep(5000)
            return@supplyAsync response.toString()
        }
    }
}