package com.example.inventory

import com.antkorwin.xsync.XSync
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class InventoryApplication {
    @Autowired
    private lateinit var bookRepo: BookRepo

    @PostConstruct
    fun init() {
        bookRepo.save(Book("1984", 1000))
        bookRepo.save(Book("Clean architecture", 1000))
    }

    @Bean
    fun xSync(): XSync<String> {
        return XSync()
    }
}

fun main(args: Array<String>) {
    runApplication<InventoryApplication>(*args)
}
