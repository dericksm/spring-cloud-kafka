package com.github.dericksm.controller;

import com.github.dericksm.model.StockDTO;
import com.github.dericksm.model.StockResponseDTO;
import com.github.dericksm.producer.StockProducer;
import com.github.dericksm.stockproducerapi.AlphaVantageAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;

@AllArgsConstructor
@RestController
@RequestMapping("/stocks")
public class StocksController {

    private final AlphaVantageAPI api;
    private final StockProducer producer;

    @GetMapping("/{ticker}")
    public ResponseEntity<StockResponseDTO> getStock(@PathVariable(required = true) String ticker) {
        ResponseEntity<StockResponseDTO> response = api.getStockDailyData(ticker);
        StockDTO stock = response.getBody().getData();
        producer.producesStockData(stock.getSymbol(), stock.getHigh(),
                stock.getLow(), stock.getOpen(), stock.getPrice(),
                stock.getDate().toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime());
        return response;
    }
}
