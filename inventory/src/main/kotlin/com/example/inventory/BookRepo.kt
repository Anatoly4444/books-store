package com.example.inventory

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepo : JpaRepository<Book, Long> {
    fun findByName(name: String) : Book
}