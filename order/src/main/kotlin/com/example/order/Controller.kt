package com.example.order

import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController("/order")
class Controller(var kafkaTemplate: KafkaTemplate<UUID, String>) {

    @PostMapping("/create/{bookName}")
    fun create(@PathVariable bookName: String) {
        print("Creating order $bookName")
        kafkaTemplate.send("order", UUID.randomUUID(), bookName)
    }
}