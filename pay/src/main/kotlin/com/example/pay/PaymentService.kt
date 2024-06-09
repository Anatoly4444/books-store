package com.example.pay

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(val mapper: ObjectMapper, val kafkaTemplate: KafkaTemplate<String, String>) {
    @Transactional
    @KafkaListener(topics = ["payment"], groupId = "payment-group")
    fun paymentListener(data: String) {
        println(data)
        val orderData = mapper.readValue(data, Order::class.java)
        reserveCredit(orderData)
        kafkaTemplate.send("order-reserved", orderData.orderId.toString(), mapper.writeValueAsString(orderData))
    }

    fun reserveCredit(order: Order){
        order.creditReserved = true
    }
}