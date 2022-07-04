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
public final class PromotionResource {
    public static final PromotionResource EMPTY = new PromotionResource();
    private Integer id;
    private String type;
    private double disCount;
    private LocalDate endDate;
}
