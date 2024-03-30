package com.basic.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Kproducer {

    Logger logger = LoggerFactory.getLogger("KafkaProducer");

    KafkaTemplate<String,String> kafkaTemplate;

    public Kproducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendText(String s){
        logger.info("sending message : " + s);
        kafkaTemplate.send("Main_Queue",s);
    }
}
