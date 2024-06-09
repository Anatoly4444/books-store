package com.example.pay

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class AppConfig {
    @Bean
    fun paymentTopic() =
        TopicBuilder.name("payment")
            .partitions(10)
            .replicas(1)
            .compact()
            .build()
}