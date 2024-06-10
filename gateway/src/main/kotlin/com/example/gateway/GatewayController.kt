package com.example.gateway

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/gateway")
class GatewayController(val restTemplate: RestTemplate) {
    @PostMapping("/order/create/{bookName}")
    @CircuitBreaker(name = "CircuitBreakerService")
    fun createOrder(@PathVariable bookName: String) {
        val response = restTemplate.postForEntity("http://localhost:8080/order/create/" + bookName, String(), Void::class.java)
        println(response)
    }
}