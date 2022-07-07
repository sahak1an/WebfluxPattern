package com.example.fluxpatterns.splitter.client;

import com.example.fluxpatterns.splitter.resource.CarReservationRequest;
import com.example.fluxpatterns.splitter.resource.CarReservationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarClient {
    private final WebClient client;

    public CarClient(@Value("${005.car}") String baseUrl) {
        client = WebClient.create(baseUrl);
    }


    public Flux<CarReservationResponse> reserve(Flux<CarReservationRequest> reservationRequestFlux){
        return client.post()
            .body(reservationRequestFlux,CarReservationRequest.class)
            .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(CarReservationResponse.class))
            .onErrorResume(throwable -> Mono.empty());
    }

}
