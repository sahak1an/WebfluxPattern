package com.example.fluxpatterns.gatewayaggregator.client;

import com.example.fluxpatterns.gatewayaggregator.resource.ProductResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {
    private final WebClient webClient;

    public ProductClient(@Value("${s0.url.product}") String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }
    
    public Mono<ProductResource> getById(int id){
       return webClient.get()
            .uri("{id}",id)
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(ProductResource.class));
    }
}
