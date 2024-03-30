package com.basic.demo;

import com.basic.demo.message.OrderCreated;

import java.util.UUID;

public class TestData {

    public static OrderCreated buildOrder(UUID uuid, String item){
        return OrderCreated.builder()
                .uuid(uuid)
                .item(item)
                .build();
    }
}
