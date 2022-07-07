package com.example.fluxpatterns.orchestrator;

import com.example.fluxpatterns.orchestrator.dto.InventoryRequest;
import com.example.fluxpatterns.orchestrator.dto.OrchestrationRequestContext;
import com.example.fluxpatterns.orchestrator.dto.PaymentRequest;
import com.example.fluxpatterns.orchestrator.dto.ShippingRequest;

public final class OrchestrationContextUtil {

    private OrchestrationContextUtil() {
    }

    public static void buildRequestContext(OrchestrationRequestContext ctx) {
        buildPaymentRequest(ctx);
        buildInventoryRequest(ctx);
        buildShippingRequest(ctx);
    }

    private static void buildPaymentRequest(OrchestrationRequestContext ctx) {
        var paymentRequest = PaymentRequest.create(
            ctx.getOrderRequest().getUserId(),
            ctx.getProductPrice() * ctx.getOrderRequest().getQuantity(),
            ctx.getOrderId()
        );
        ctx.setPaymentRequest(paymentRequest);
    }

    private static void buildInventoryRequest(OrchestrationRequestContext ctx) {
        var inventoryRequest = InventoryRequest.create(
            ctx.getOrderId(),
            ctx.getOrderRequest().getProductId(),
            ctx.getOrderRequest().getQuantity()
        );
        ctx.setInventoryRequest(inventoryRequest);
    }

    private static void buildShippingRequest(OrchestrationRequestContext ctx) {
        var shippingRequest = ShippingRequest.create(
            ctx.getOrderRequest().getQuantity(),
            ctx.getOrderRequest().getUserId(),
            ctx.getOrderId()
        );
        ctx.setShippingRequest(shippingRequest);
    }

}
