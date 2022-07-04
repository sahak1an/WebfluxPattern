package com.example.fluxpatterns.gatewayaggregator.resource;

import java.time.LocalDate;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class Price {
    private final int listPrice;
    private final double discount;
    private final double discountPrice;
    private final double amountSaved;
    private final LocalDate endDate;
}
