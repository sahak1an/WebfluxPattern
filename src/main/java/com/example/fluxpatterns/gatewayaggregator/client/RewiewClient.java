package com.example.fluxpatterns.gatewayaggregator.client;

import java.util.List;

import com.example.fluxpatterns.gatewayaggregator.resource.PromotionResource;
import com.example.fluxpatterns.gatewayaggregator.resource.ReviewResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RewiewClient {

    private final WebClient webClient;

    public RewiewClient(@Value("${s0.url.review}") String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<List<ReviewResource>> getById(int id){
        return webClient.get()
            .uri("{id}",id)
            .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(ReviewResource.class))
            .collectList();
    }
}
