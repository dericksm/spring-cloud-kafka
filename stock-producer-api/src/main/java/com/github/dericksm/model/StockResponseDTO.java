package com.github.dericksm.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockResponseDTO {

    @JsonAlias({"Global Quote"})
    StockDTO data;
}
