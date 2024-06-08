package com.example.order

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/order")
class Controller {

    @PostMapping("/create/{bookName}")
    fun create(@PathVariable bookName: String) {
        print("Creating order $bookName")
    }
}