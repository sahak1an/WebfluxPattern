package com.example.fluxpatterns.sgp.service;

import java.time.Duration;

import com.example.fluxpatterns.sgp.client.DeltaClient;
import com.example.fluxpatterns.sgp.client.JetBlueClient;
import com.example.fluxpatterns.sgp.resource.FlightResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final DeltaClient deltaClient;
    private final JetBlueClient jetBlueClient;

    public Flux<FlightResult> getFlights(String from, String to) {
        return Flux.merge(
                deltaClient.getFlights(from, to),
                jetBlueClient.getFlights(from, to)
            )
            .delayElements(Duration.ofMillis(1));
    }
}
