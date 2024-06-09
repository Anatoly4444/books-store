package com.example.order

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping("/order")
class Controller(val kafkaTemplate: KafkaTemplate<String, String>, val mapper: ObjectMapper) {
    private val orders = ConcurrentHashMap<UUID, Order>()

    @PostMapping("/create/{bookName}")
    fun create(@PathVariable bookName: String) {
        print("Creating order $bookName")
        val userId = UUID.randomUUID()
        val orderId = UUID.randomUUID()
        val order = Order(
            orderId, bookName, userId, Status.PENDING
        )
        orders.put(orderId, order)
        val orderJson = mapper.writeValueAsString(order)
        kafkaTemplate.send("order", orderId.toString(), orderJson)
    }
}