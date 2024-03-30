package com.basic.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkatopicConfig {
    @Bean
    public NewTopic mainTopic(){
        return TopicBuilder.name("Main_Queue")
                .build();
    }
}
