package com.basic.demo;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Kconsumer {
    @KafkaListener(id = "myId", topics = "Main_Queue")
    public void listen(String in) {
        System.out.println(in);
    }


    @KafkaListener(id = "myId2", topics = "Main_Queue")
    public void listena(String in) {
        System.out.println(in + "2");
    }



    @KafkaListener(id = "myId3", topics = "Main_Queue")
    public void listeni(String in) {
        System.out.println(in + "3");
    }

}
