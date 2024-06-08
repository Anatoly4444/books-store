package com.example.inventory

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestInventoryApplication

fun main(args: Array<String>) {
    fromApplication<InventoryApplication>().with(TestInventoryApplication::class).run(*args)
}
