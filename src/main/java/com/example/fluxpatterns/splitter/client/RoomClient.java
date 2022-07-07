package com.example.fluxpatterns.splitter.client;

import com.example.fluxpatterns.splitter.resource.CarReservationRequest;
import com.example.fluxpatterns.splitter.resource.CarReservationResponse;
import com.example.fluxpatterns.splitter.resource.RoomReservationRequest;
import com.example.fluxpatterns.splitter.resource.RoomReservationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomClient {

    private final WebClient client;

    public RoomClient(@Value("${005.car}") String baseUrl) {
        client = WebClient.create(baseUrl);
    }


    public Flux<RoomReservationResponse> reserve(Flux<CarReservationRequest> reservationRequestFlux){
        return client.post()
            .body(reservationRequestFlux, RoomReservationRequest.class)
            .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(RoomReservationResponse.class))
            .onErrorResume(throwable -> Mono.empty());
    }
}
