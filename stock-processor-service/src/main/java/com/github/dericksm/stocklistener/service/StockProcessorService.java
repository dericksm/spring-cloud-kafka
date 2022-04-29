package com.github.dericksm.stocklistener.service;

import com.github.dericksm.stocklistener.model.ClosingValue;
import org.springframework.stereotype.Service;

@Service
public class StockProcessorService {

    public String stockClosingValueProcessor(Double open, Double close) {
        Double expectedGrowth = open * 0.03;
        Double currentGrowth = close - open;
        if (currentGrowth >= expectedGrowth) {
            return ClosingValue.UP.name();
        } else if(close >= open)  {
            return ClosingValue.NEUTRAL.name();
        } else {
            return ClosingValue.DOWN.name();
        }
    }
}


