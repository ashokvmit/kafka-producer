package com.ashok.kafka.controller;

import com.ashok.kafka.model.Customer;
import com.ashok.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/sendMessage")
    private ResponseEntity<?> sendMessageToKafka(@RequestParam(required = true) String message,
                                                 @RequestParam(required = true) Integer partition) {
        kafkaProducerService.sendToKafka(message, partition);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/sendCustomer")
    private ResponseEntity<?> sendCustomerToKafka(@RequestBody Customer customer) {
        kafkaProducerService.sendCustomerToKafka(customer);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
