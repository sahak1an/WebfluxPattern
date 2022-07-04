package com.example.fluxpatterns.gatewayaggregator.resource;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class ProductAggregator {
    private final int id;
    private final String type;
    private final String description;
    private final Price price;
    private final List<ReviewResource> reviews;
}
