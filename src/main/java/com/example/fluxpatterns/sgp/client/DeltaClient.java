package com.example.fluxpatterns.sgp.client;

import com.example.fluxpatterns.sgp.resource.FlightResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DeltaClient {
private final WebClient webClient;

    public DeltaClient(@Value("${delta.service}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public Flux<FlightResult> getFlights(String from,String to){
        return webClient.get()
            .uri("{from}/{to}",from,to)
            .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(FlightResult.class))
            .onErrorResume(throwable -> Mono.empty());
    }
}
