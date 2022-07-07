package com.example.fluxpatterns.ratelimiter;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CalculateController {

    @GetMapping("/calculate/{n}")
    @RateLimiter(name = "calc-service", fallbackMethod = "fallback")
    public Mono<ResponseEntity<Integer>> calc(@PathVariable int n){
        return Mono.create(responseEntityMonoSink -> {
            int sum = (int) Math.pow(n, 2);
            responseEntityMonoSink.success(ResponseEntity.ok(sum));
        });
    }

    @SuppressWarnings("unused")
    public Mono<ResponseEntity<?>> fallback(@PathVariable int n,Throwable ex){
        System.out.println(ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex.getMessage()));
    }
}
