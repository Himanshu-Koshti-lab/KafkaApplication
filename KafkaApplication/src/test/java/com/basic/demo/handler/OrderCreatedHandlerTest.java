package com.basic.demo.handler;

import com.basic.demo.TestData;
import com.basic.demo.message.OrderCreated;
import com.basic.demo.service.DispatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderCreatedHandlerTest {
    private OrderCreatedHandler handler;
    private DispatchService dispatchService;


    @BeforeEach
    void setUp(){
        dispatchService = mock(DispatchService.class);
        handler = new OrderCreatedHandler(dispatchService);
    }

    @Test
    void listen() {
        OrderCreated orderCreated = TestData.buildOrder(randomUUID(),randomUUID().toString());
        handler.listen(orderCreated);
        verify(dispatchService, times(1)).process(orderCreated);
    }
}