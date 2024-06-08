package com.example.inventory

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Book (
    val name: String,
    @Id
    @GeneratedValue
    val id: Int? = null
)
