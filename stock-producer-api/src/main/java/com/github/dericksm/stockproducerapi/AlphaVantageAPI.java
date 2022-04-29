package com.github.dericksm.stockproducerapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dericksm.model.StockResponseDTO;
import com.github.dericksm.producer.StockProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlphaVantageAPI {

    @Value(value = "${alphavantage.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s";

    private final StockProducer stockProducer;
    private final ObjectMapper objectMapper;

    public AlphaVantageAPI(RestTemplateBuilder restTemplateBuilder, StockProducer stockProducer) {
        this.restTemplate = restTemplateBuilder.build();
        this.stockProducer = stockProducer;
        this.objectMapper = new ObjectMapper();
    }

    public ResponseEntity<StockResponseDTO> getStockDailyData(String ticker) {
        return restTemplate.getForEntity(String.format(BASE_URL, ticker, apiKey), StockResponseDTO.class);
    }
}
