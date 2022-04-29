package com.github.dericksm.stockelasticapi.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SearchRequest {
    @NotBlank
    private String text;
}
