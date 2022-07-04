package com.example.fluxpatterns.gatewayaggregator.resource;

import java.time.LocalDate;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class PromotionResource {
    private final Integer id;
    private final String type;
    private final double disCount;
    private final LocalDate endDate;
}
