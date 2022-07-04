package com.example.fluxpatterns.gatewayaggregator.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ProductResource {
    public static final ProductResource EMPTY = new ProductResource();
    private int id;
    private String type;
    private String description;
    private double price;
}
