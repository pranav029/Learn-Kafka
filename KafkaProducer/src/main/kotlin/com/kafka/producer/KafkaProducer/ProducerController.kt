package com.kafka.producer.KafkaProducer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class ProducerController {

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @GetMapping("/produce/{message}")
    fun sendMessage(@PathVariable message: String): ResponseEntity<String> {
        kafkaTemplate.send(KafkaProducerConfig.KAFKA_TOPIC, message)
        return ResponseEntity.ok("Success! $message sent")
    }
}