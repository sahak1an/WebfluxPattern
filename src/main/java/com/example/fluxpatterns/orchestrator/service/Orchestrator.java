package com.example.fluxpatterns.orchestrator.service;

import java.util.function.Consumer;
import java.util.function.Predicate;

import com.example.fluxpatterns.orchestrator.dto.OrchestrationRequestContext;
import reactor.core.publisher.Mono;

public abstract class Orchestrator {

    public abstract Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx);

    public abstract Predicate<OrchestrationRequestContext> isSucces();

    public abstract Consumer<OrchestrationRequestContext> cancel();

}
