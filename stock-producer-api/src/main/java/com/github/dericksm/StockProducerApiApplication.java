package com.github.dericksm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class StockProducerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockProducerApiApplication.class, args);
    }

}
