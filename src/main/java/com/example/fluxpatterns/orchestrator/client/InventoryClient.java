package com.example.fluxpatterns.orchestrator.client;

import com.example.fluxpatterns.orchestrator.dto.InventoryRequest;
import com.example.fluxpatterns.orchestrator.dto.InventoryResponse;
import com.example.fluxpatterns.orchestrator.dto.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InventoryClient {

    private static final String DEDUCT = "deduct";
    private static final String RESTORE = "restore";
    private final WebClient client;

    public InventoryClient(@Value("${sec03.inventory.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    public Mono<InventoryResponse> deduct(InventoryRequest request){
        return this.callInventoryService(DEDUCT, request);
    }

    public Mono<InventoryResponse> restore(InventoryRequest request){
        return this.callInventoryService(RESTORE, request);
    }

    private Mono<InventoryResponse> callInventoryService(String endPoint, InventoryRequest request){
        return this.client
                .post()
                .uri(endPoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(InventoryResponse.class)
                .onErrorReturn(this.buildErrorResponse(request));
    }

    private InventoryResponse buildErrorResponse(InventoryRequest request){
        return InventoryResponse.create(
            request.getProductId(),
            request.getQuantity(),
            null,
            Status.FAILED
        );
    }

}