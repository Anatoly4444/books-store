package com.example.payment

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService {
    @Transactional
    @KafkaListener(topics = ["payment"], groupId = "payment-group")
    fun paymentListener(data: String) {
        reserveCredit()
    }

    fun reserveCredit(){}
}