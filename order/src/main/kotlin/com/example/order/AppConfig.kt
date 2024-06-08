package com.example.order

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class AppConfig {

    @Bean
    fun orderTopic() =
        TopicBuilder.name("order")
            .partitions(10)
            .replicas(1)
            .compact()
            .build()
}