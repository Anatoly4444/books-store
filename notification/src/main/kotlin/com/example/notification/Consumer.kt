package com.example.notification

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class Consumer {
    @KafkaListener(topics = [ "order" ], groupId = "notification-group")
    fun orderListener(message: String) {
        println("Message '$message' received.")
    }
}