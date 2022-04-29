package com.github.dericksm.stockelasticapi.repository;

import com.github.dericksm.stockelasticapi.model.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockElasticRepository extends ElasticsearchRepository<Stock, UUID> {

    Page<Stock> findStockByIdOrSymbol(UUID  id, String symbol, Pageable pageable);
}
