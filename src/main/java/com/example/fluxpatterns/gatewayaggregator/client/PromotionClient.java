package com.example.fluxpatterns.gatewayaggregator.client;

import static com.example.fluxpatterns.gatewayaggregator.resource.PromotionResource.EMPTY;

import com.example.fluxpatterns.gatewayaggregator.resource.PromotionResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PromotionClient {
    private final WebClient webClient;

    public PromotionClient(@Value("${s0.url.promotion}") String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<PromotionResource> getById(int id) {
        return webClient.get()
            .uri("{id}", id)
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(PromotionResource.class))
            .switchIfEmpty(Mono.just(EMPTY))
            .onErrorResume(e -> Mono.error(new RuntimeException()));
    }
}
