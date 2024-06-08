package com.example.inventory

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Reservation(
    val name: String,
    val userId: String,
    @Id
    @GeneratedValue
    var id: Long? = null)