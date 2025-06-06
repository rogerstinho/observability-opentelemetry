package com.fit.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PayrollMessageProducerService {
    private final KafkaTemplate<String, PayrollMessage> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topicName ;

    public PayrollMessageProducerService(KafkaTemplate<String, PayrollMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PayrollMessage msg) {
        kafkaTemplate.send(topicName, UUID.randomUUID().toString(), msg);
    }
}
