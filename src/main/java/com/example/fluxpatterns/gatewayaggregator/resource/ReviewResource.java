package com.example.fluxpatterns.gatewayaggregator.resource;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class ReviewResource {
    private final int id;
    private final String user;
    private final int rating;
    private final String comment;
}