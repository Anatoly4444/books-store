package com.example.inventory

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import lombok.Setter

@Entity
data class Book (
    val name: String,
    var amount: Int,
    @Id
    @GeneratedValue
    val id: Long? = null
)
