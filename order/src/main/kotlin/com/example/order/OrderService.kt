package com.example.order

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class OrderService(val kafkaTemplate: KafkaTemplate<String, String>,
                   val mapper: ObjectMapper) {
    private val orders = ConcurrentHashMap<UUID, Order>()

    @KafkaListener(topics = ["order-reserved"], groupId = "order-reserved-group")
    fun reservedOrderListener(data: String) {
        val dataOrder = mapper.readValue(data, Order::class.java)
        val order = orders.get(dataOrder.orderId)
        order!!.status = Status.APPROVED
        println("Order approved #$order")

    }

    fun processNewOrder(bookName: String) {
        println("Creating order $bookName")
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