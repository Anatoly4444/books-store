package com.example.inventory

import com.antkorwin.xsync.XSync
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(val bookRepo: BookRepo,
                  val reservationRepo: ReservationRepo,
                  val xSync: XSync<String>,
                  val mapper: ObjectMapper,
                  val kafkaTemplate: KafkaTemplate<String, String>
    ) {

    @Transactional
    @KafkaListener(topics = [ "order" ], groupId = "reservation-group", concurrency = "2")
    fun orderListener(data: String) {
        val orderData = mapper.readValue(data, Order::class.java)
        val bookName = orderData.bookName
        xSync.execute(bookName) {
            throw RuntimeException("I'm runtime. Haha")
            reserveBook(bookName, orderData)
            orderData.reserved = true
            kafkaTemplate.send("payment", orderData.orderId.toString(), mapper.writeValueAsString(orderData))
        }
    }

    private fun reserveBook(bookName: String, orderData: Order) {
        val book: Book = bookRepo.findByName(bookName)
        book.amount = book.amount - 1
        bookRepo.save(book)
        val userId = orderData.userId.toString()
        reservationRepo.save(Reservation(book.name, userId))
        println("Book $bookName reserved for user #$userId")
    }

    private fun cancelReservation(){}
}