package com.fit.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit.clients.BankAccountClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
public class PayrollMessageConsumerListener {

    private final ObjectMapper objectMapper;
    private final BankAccountClient bankAccountClient;

    public PayrollMessageConsumerListener(BankAccountClient bankAccountClient) {
        this.bankAccountClient = bankAccountClient;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(
            topics = "${spring.kafka.consumer.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    @RetryableTopic(
            attempts = "${spring.kafka.consumer.retry.max-attempts}"
    )
    public void listen(String message) throws JsonProcessingException {
        PayrollMessage payrollMessage = objectMapper.readValue(message, PayrollMessage.class);

        System.out.println(payrollMessage);

        bankAccountClient.creditBankAccount(payrollMessage.accountNumber());
    }
}
