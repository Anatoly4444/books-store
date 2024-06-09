package com.example.inventory

import java.util.*

data class Order(
    val orderId: UUID,
    val bookName: String,
    val userId: UUID,
    var reserved: Boolean = false,
    var status: Status = Status.NEW
    )

enum class Status {
    NEW, PENDING, APPROVED
}