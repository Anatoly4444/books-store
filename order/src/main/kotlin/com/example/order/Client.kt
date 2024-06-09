package com.example.order

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("inventory")
interface InventoryClient {
    @GetMapping
    fun getItem(name: String)
}