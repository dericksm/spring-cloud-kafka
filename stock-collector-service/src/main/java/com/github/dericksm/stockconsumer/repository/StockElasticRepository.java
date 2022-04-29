package com.github.dericksm.stockconsumer.repository;//package com.github.dericksm.stockconsumer.repository;

import com.github.dericksm.stockconsumer.model.entity.Stock;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockElasticRepository extends ElasticsearchRepository<Stock, UUID> {
}
