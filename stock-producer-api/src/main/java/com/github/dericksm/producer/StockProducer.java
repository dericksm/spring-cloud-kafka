package com.github.dericksm.producer;

import com.github.dericksm.commons.schema.StockData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class StockProducer {

    private final StreamBridge streamBridge;

    public void producesStockData(String symbol, Double high, Double low, Double open, Double close, OffsetDateTime date) {
        StockData stockData = new StockData();
        stockData.setId(UUID.randomUUID().toString());
        stockData.setSymbol(symbol);
        stockData.setHigh(high);
        stockData.setLow(low);
        stockData.setOpen(open);
        stockData.setClose(close);
        stockData.setDate(date.toString());

        Message<StockData> message = MessageBuilder
                .withPayload(stockData)
                .setHeader("partitionKey", stockData.getId())
                .build();

        streamBridge.send("stocks-out-0", message, MimeType.valueOf("application/*+avro"));
    }

}