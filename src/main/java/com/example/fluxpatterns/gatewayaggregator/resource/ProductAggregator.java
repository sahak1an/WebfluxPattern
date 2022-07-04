package com.example.fluxpatterns.gatewayaggregator.resource;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ProductAggregator {
    private int id;
    private String type;
    private String description;
    private Price price;
    private List<ReviewResource> reviews;
}
