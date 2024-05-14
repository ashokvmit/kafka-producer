package com.ashok.kafka.serviceimpl;

import com.ashok.kafka.model.Customer;
import com.ashok.kafka.service.KafkaProducerService;
import com.ashok.kafka.util.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void sendToKafka(String message, Integer partition) {
        kafkaProducer.sendToKafka(message, partition);
    }

    @Override
    public void sendCustomerToKafka(Customer customer) {
        kafkaProducer.sendCustomerToKafka(customer);
    }
}
