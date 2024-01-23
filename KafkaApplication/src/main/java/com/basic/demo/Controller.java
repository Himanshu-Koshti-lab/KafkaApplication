package com.basic.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Kproducer kproducer;

    public Controller(Kproducer kproducer) {
        this.kproducer = kproducer;
    }

    @PostMapping("/publish")
    public String sendMessage(@RequestParam("message") String message){
        kproducer.sendText(message);
        return "Done";
    }
}
