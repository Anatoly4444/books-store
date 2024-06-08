package com.example.inventory

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inventory")
class Controller(val bookService: BookService) {

    @GetMapping("/book/{name}")
    fun reserveItem(@PathVariable name: String, userId: String) {
        bookService.reserveItem(name, userId)
    }
}