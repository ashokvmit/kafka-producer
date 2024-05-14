package com.ashok.kafka.service;

import com.ashok.kafka.model.Customer;

public interface KafkaProducerService {
    public void sendToKafka(String message, Integer partition);

    public void sendCustomerToKafka(Customer customer);
}
