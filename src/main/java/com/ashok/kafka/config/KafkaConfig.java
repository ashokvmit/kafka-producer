package com.ashok.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createMessageTopic() {
        return new NewTopic("spring-topic-1", 3, (short) 1);
    }

    @Bean
    public NewTopic createCustomerTopic() {
        return new NewTopic("spring-topic-customer", 2, (short) 1);
    }

    @Bean
    public Map<String, Object> getKafkaProperties() {
        Map<String, Object> props = new LinkedHashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, Object> getProducerFactory() {
        return new DefaultKafkaProducerFactory<>(getKafkaProperties());
    }

    @Bean
    public KafkaTemplate<String, Object> getKafkaTemplate() {
        return new KafkaTemplate<>(getProducerFactory());
    }

}
