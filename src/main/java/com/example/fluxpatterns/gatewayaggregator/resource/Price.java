package com.example.fluxpatterns.gatewayaggregator.resource;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class Price {
    private static final Price EMPTY = new Price();

    private int listPrice;
    private double discount;
    private double discountPrice;
    private double amountSaved;
    private LocalDate endDate;
}
