package com.example.fluxpatterns.orchestrator.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class OReqContext {
    private final UUID orderId = UUID.randomUUID();
    private OrderRequest orderRequest;
    private int productPrice;
    private PaymentRequest paymentRequest;
    private PaymentResponse paymentResponse;
    private InventoryResponse inventoryResponse;
    private InventoryRequest inventoryRequest;
    private ShippingRequest shippingRequest;
    private ShippingResponse shippingResponse;
    private Status status;

    public OReqContext(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }
}
