package com.basic.demo.service;

import com.basic.demo.TestData;
import com.basic.demo.message.OrderCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class DispatchServiceTest {

    private DispatchService dispatchService;

    @BeforeEach
    void setUp() {
        dispatchService = new DispatchService();
    }

    @Test
    void process() {
        OrderCreated orderCreated = TestData.buildOrder(randomUUID(),randomUUID().toString());
        dispatchService.process(orderCreated);
    }
}