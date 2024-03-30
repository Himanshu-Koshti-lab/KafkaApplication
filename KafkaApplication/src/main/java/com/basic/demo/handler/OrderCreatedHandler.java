package com.basic.demo.handler;

import com.basic.demo.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedHandler {

    private final DispatchService dispatchService;

    @KafkaListener(id = "orderConsumerClient", topics = "order.created",
            groupId = "dispatch.order.created.consumer")
    public void listen(String payload) {
        log.info("Message Received " + payload);
        dispatchService.process(payload);
    }
}
