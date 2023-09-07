package com.kafka.consumer.KafkaConsumer

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

class KafkaConsumerConfig {
    @Bean
    fun kafkaAdmin(): KafkaAdmin = HashMap<String, Any>().run {
        put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_ADDRESS)
        KafkaAdmin(this)
    }

    @Bean
    fun topic(): NewTopic = NewTopic(KAFKA_TOPIC, 1, 1)

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps.apply {
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_ADDRESS)
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer::class)
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer::class)
        }
        return DefaultKafkaConsumerFactory(configProps)
    }

    @Bean
    fun kafkaListener(): ConcurrentKafkaListenerContainerFactory<String, String> = ConcurrentKafkaListenerContainerFactory<String, String>().apply {
        consumerFactory = consumerFactory()
    }

    companion object {
        const val BOOTSTRAP_ADDRESS = "localhost:9092"
        const val KAFKA_TOPIC = "FirstTopic"
    }
}