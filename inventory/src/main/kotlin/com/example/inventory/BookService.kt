package com.example.inventory

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Slf4j
@Service
class BookService(var bookRepo: BookRepo, var reservationRepo: ReservationRepo) {
    @Transactional
    fun reserveItem(name: String, userId: String) {
        synchronized(name.intern()) {
            val book : Book = bookRepo.findByName(name)
            book.amount = book.amount - 1
            bookRepo.save(book)
            reservationRepo.save(Reservation(book.name, userId))
        }
    }
}