package com.github.dericksm.stockconsumer;

import com.github.dericksm.commons.schema.StockData;
import com.github.dericksm.stockconsumer.model.entity.Stock;
import com.github.dericksm.stockconsumer.repository.StockElasticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class StockStream {

    private final StockElasticRepository stockElasticRepository;

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

            stockElasticRepository.save(toStock(stock));

            return MessageBuilder.withPayload(stock)
                    .setHeader("partitionKey", stock.getId())
                    .build();
        };
    }

    private Stock toStock(StockData stockData) {
        Stock stock = new Stock();
        stock.setId(UUID.randomUUID());
        stock.setGrowth(stockData.getGrowth().toString());
        stock.setPrice(Double.valueOf(stockData.getClose()));
        stock.setSymbol(stockData.getSymbol().toString());
        stock.setDate(OffsetDateTime.parse(stockData.getDate()));
        return stock;
    }
}
