package com.example.fluxpatterns.gatewayaggregator.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ReviewResource {
    private static final ReviewResource EMPTY = new ReviewResource();
    private int id;
    private String user;
    private int rating;
    private String comment;
}
