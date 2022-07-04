package com.example.fluxpatterns.sgp.client;

import com.example.fluxpatterns.sgp.resource.FlightResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JetBlueClient {
    private static final String JETBLUE = "JETBLUE";
    private final WebClient webClient;

    public JetBlueClient(@Value("${jet.service}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public Flux<FlightResult> getFlights(String from, String to) {
        return webClient.get()
            .uri("{from}/{to}", from, to)
            .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(FlightResult.class))
            .doOnNext(result -> result.toBuilder().airLine(JETBLUE).build())
            .onErrorResume(throwable -> Mono.empty());
    }
}
