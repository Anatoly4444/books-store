package com.example.gateway

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@RestController
@RequestMapping("/gateway")
class GatewayController(val gatewayService: GatewayService) {
    @PostMapping("/order/create/{bookName}")
    @CircuitBreaker(name = "circuitBreaker.createOrder")
    @Retry(name = "retry.createOrder")
    @TimeLimiter(name = "timeLimiter.createOrder")
    @Bulkhead(name="bulkhead.createOrder")
    @RateLimiter(name = "rateLimiter.createOrder")
    fun createOrder(@PathVariable bookName: String): CompletionStage<String> {
        return gatewayService.createOrder(bookName)
    }
}