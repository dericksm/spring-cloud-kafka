package com.github.dericksm.stockelasticapi.controller;

import com.github.dericksm.stockelasticapi.controller.dto.request.SearchRequest;
import com.github.dericksm.stockelasticapi.model.entity.Stock;
import com.github.dericksm.stockelasticapi.repository.StockElasticRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/stocks")
@AllArgsConstructor
public class FindStocksController {

    private final StockElasticRepository stockElasticRepository;

    @GetMapping
    public Page<Stock> getStocks(Pageable pageable) {
        return stockElasticRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Stock getNewsById(@PathVariable UUID id) throws Exception {
        return stockElasticRepository.findById(id).orElseThrow(() -> new Exception(String.format("Stock %s not found", id)));
    }

    @PostMapping("/search")
    public Page<Stock> searchNews(@Valid @RequestBody SearchRequest searchRequest, Pageable pageable) {
        return stockElasticRepository.findStockByIdOrSymbol(UUID.fromString(searchRequest.getText()), searchRequest.getText(), pageable);
    }
}

