package com.example.fluxpatterns.splitter.service;

import com.example.fluxpatterns.splitter.client.CarClient;
import com.example.fluxpatterns.splitter.resource.CarReservationRequest;
import com.example.fluxpatterns.splitter.resource.ReservationItemRequest;
import com.example.fluxpatterns.splitter.resource.ReservationItemResponse;
import com.example.fluxpatterns.splitter.resource.ReservationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CarResHandler extends ReservationHandler{

    @Autowired
    CarClient carClient;

    @Override
    protected ReservationType getType() {
        return ReservationType.CAR;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> requestFlux) {
         requestFlux
                .map(this::toCarRequest)
                .transform(carClient::reserve);
         return null;
    }

    private CarReservationRequest toCarRequest(ReservationItemRequest itemRequest){
        return CarReservationRequest.create(
            itemRequest.getCity(),
            itemRequest.getFrom(),
            itemRequest.getTo(),
            itemRequest.getCategory()
        );
    }
}
