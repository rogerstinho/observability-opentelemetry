package com.fit;


import com.fit.messaging.PayrollMessageProducerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayrollServiceApplication implements ApplicationRunner {

//    private final PayrollMessageProducerService service;

    public PayrollServiceApplication(PayrollMessageProducerService service) {
//        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(PayrollServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("Kafka log send starting...");
//        for (int i = 0; i < 2; i++) {
//            service.sendMessage(new PayrollMessage("1234567890", 1000L));
//        }
//        System.out.println("Kafka log sent...");
    }
}