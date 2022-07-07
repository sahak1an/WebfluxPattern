package com.example.fluxpatterns.cb.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class Product {
    private Integer id;
    private String category;
    private String description;
    private Integer price;
}
