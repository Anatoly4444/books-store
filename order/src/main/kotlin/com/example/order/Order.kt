package com.example.order

import java.util.*

data class Order(
    val orderId: UUID,
    val bookName: String,
    val userId: UUID,
    var status: Status = Status.NEW,
    var reserved: Boolean = false,
    )

enum class Status {
    NEW, PENDING, APPROVED
}