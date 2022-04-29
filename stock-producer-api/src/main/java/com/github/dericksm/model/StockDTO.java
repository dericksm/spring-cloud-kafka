package com.github.dericksm.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class StockDTO {

    @JsonAlias({"01. symbol"})
    private String symbol;
    @JsonAlias({"02. open"})
    private Double open;
    @JsonAlias({"03. high"})
    private Double high;
    @JsonAlias({"04. low"})
    private Double low;
    @JsonAlias({"05. price"})
    private Double price;
    @JsonAlias({"07. latest trading day"})
    private Date date;
}
