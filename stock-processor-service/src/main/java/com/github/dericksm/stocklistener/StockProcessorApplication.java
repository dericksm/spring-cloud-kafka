package com.github.dericksm.stocklistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@SpringBootApplication
public class StockProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockProcessorApplication.class, args);
    }

}
