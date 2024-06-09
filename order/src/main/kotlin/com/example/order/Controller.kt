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
class Controller(val orderService: OrderService) {


    @PostMapping("/create/{bookName}")
    fun create(@PathVariable bookName: String) {
        orderService.processNewOrder(bookName)
    }


}