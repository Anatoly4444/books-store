package com.example.inventory

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InventoryApplication {
    @Autowired
    private lateinit var bookRepo: BookRepo

    @PostConstruct
    fun init() {
        bookRepo.save(Book("1984"))
        bookRepo.save(Book("Clean architecture"))
    }
}

fun main(args: Array<String>) {
    runApplication<InventoryApplication>(*args)
}
