package com.example.inventory

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/inventory")
class Controller(val bookService: BookService) {

    @PostMapping("/reserve/book/{name}")
    fun reserveItem(@PathVariable name: String, userId: String) {
    }
}