package com.github.dericksm.stockconsumer.model.entity;//package com.github.dericksm.stockconsumer.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Document(indexName = "stock")
public class Stock {

    @Id
    private UUID id;
    @Field(type = FieldType.Text)
    private String symbol;
    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private OffsetDateTime date;
    @Field(type = FieldType.Text)
    private String growth;
    @Field(type = FieldType.Double)
    private Double price;
}
