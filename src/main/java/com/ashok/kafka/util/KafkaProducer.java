package com.ashok.kafka.util;

import com.ashok.kafka.model.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Log4j2
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemple;

    public void sendToKafka(String message, Integer partition) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemple.send("spring-topic-1", partition, null, message);
            future.whenComplete((response, ex) -> {
                if (null != ex) {
                    log.error("Error in producing message : {} with exception {}", message, ex.getMessage());
                } else {
                    log.info("Message produced successfully [ message : {} , offset : {} ]", message,
                            response.getRecordMetadata().offset());
                }
            });
        } catch (Exception e) {
            log.error("Error in producing message {} with error {}", message, e.getMessage());
        }
    }

    public void sendCustomerToKafka(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemple.send("spring-topic-customer", customer);
            future.whenComplete((response, ex) -> {
                if (null != ex) {
                    log.error("Error in producing customer : {} with exception {}", customer.toString(), ex.getMessage());
                } else {
                    log.info("Message produced successfully [ customer : {} , offset : {} ]", customer.toString(),
                            response.getRecordMetadata().offset());
                }
            });
        } catch (Exception e) {
            log.error("Error in producing customer {} with error {}", customer.toString(), e.getMessage());
        }
    }
}
