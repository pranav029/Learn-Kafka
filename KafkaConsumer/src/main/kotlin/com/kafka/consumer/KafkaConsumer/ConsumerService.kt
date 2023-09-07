package com.kafka.consumer.KafkaConsumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class ConsumerService {

    @KafkaListener(topics = [KafkaConsumerConfig.KAFKA_TOPIC], groupId = "foo")
    fun consumer(@Payload message: String) {
        println(message)
    }
}