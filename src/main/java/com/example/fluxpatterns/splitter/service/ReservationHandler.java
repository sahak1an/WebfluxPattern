package com.example.fluxpatterns.splitter.service;

import com.example.fluxpatterns.splitter.resource.ReservationItemRequest;
import com.example.fluxpatterns.splitter.resource.ReservationItemResponse;
import com.example.fluxpatterns.splitter.resource.ReservationType;
import reactor.core.publisher.Flux;

public abstract class ReservationHandler {
    protected abstract ReservationType getType();
    protected abstract Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> requestFlux);
}
