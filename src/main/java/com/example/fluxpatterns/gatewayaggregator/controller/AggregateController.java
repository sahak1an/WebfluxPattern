package com.example.fluxpatterns.gatewayaggregator.controller;

import com.example.fluxpatterns.gatewayaggregator.resource.ProductAggregator;
import com.example.fluxpatterns.gatewayaggregator.service.ProductAggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sec01")
@RequiredArgsConstructor
public class AggregateController {
    private final ProductAggregatorService aggregatorService;

    @GetMapping("/product/{id}")
    public Mono<ResponseEntity<ProductAggregator>> aggregare(@PathVariable Integer id) {
        return aggregatorService.aggregate(id)
            .map(productAggregator -> ResponseEntity.ok().body(productAggregator))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
