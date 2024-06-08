package com.example.order

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/order")
class Controller(var kafkaTemplate: KafkaTemplate<String, String>) {

    @PostMapping("/create/{bookName}")
    fun create(@PathVariable bookName: String) {
        print("Creating order $bookName")
        kafkaTemplate.send("order", UUID.randomUUID().toString(), bookName)
    }
}