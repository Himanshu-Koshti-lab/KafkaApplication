package com.basic.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Kconsumer {
    @KafkaListener(id = "myId", topics = "Topic")
    public void listen(String in) {
        System.out.println(in);
    }
}
