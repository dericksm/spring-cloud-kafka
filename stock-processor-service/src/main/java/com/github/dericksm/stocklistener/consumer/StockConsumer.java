package com.github.dericksm.stocklistener.consumer;

import com.github.dericksm.commons.schema.StockData;
import com.github.dericksm.stocklistener.service.StockProcessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Component
@Slf4j
@AllArgsConstructor
public class StockConsumer {

    private final StockProcessorService  stockProcessorService;

    @Bean
    public Function<Message<StockData>, Message<StockData>> stocks() {
        return message -> {
            StockData stock = message.getPayload();
            MessageHeaders messageHeaders = message.getHeaders();
            log.info("StockData with id '{}' and title '{}' received from bus. topic: {}, partition: {}, offset: {}, deliveryAttempt: {}",
                    stock.getSymbol(),
                    messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
                    messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class),
                    messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
                    messageHeaders.get(IntegrationMessageHeaderAccessor.DELIVERY_ATTEMPT, AtomicInteger.class));

            String growth = stockProcessorService.stockClosingValueProcessor(stock.getOpen(), stock.getClose());
            stock.setGrowth(growth);

            return MessageBuilder.withPayload(stock)
                    .setHeader("partitionKey", stock.getId())
                    .build();
        };
    }
}
