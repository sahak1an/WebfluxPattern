package com.example.fluxpatterns.orchestrator.service;

import java.util.function.Consumer;
import java.util.function.Predicate;

import com.example.fluxpatterns.orchestrator.client.UserClient;
import com.example.fluxpatterns.orchestrator.dto.OrchestrationRequestContext;
import com.example.fluxpatterns.orchestrator.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class PaymentOrchestrator extends Orchestrator{

    @Autowired
    UserClient client;

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
        return client.deduct(ctx.getPaymentRequest())
            .doOnNext(ctx::setPaymentResponse)
            .thenReturn(ctx);
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSucces() {
        return ctx -> Status.SUCCESS.equals(ctx.getPaymentResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return ctx -> Mono.just(ctx).filter(isSucces())
            .map(OrchestrationRequestContext::getPaymentRequest)
            .flatMap(paymentRequest -> client.refund(paymentRequest))
            .subscribe();
    }
}
