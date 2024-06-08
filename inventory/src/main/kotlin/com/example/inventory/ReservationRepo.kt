package com.example.inventory

import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepo : JpaRepository<Reservation, Long>