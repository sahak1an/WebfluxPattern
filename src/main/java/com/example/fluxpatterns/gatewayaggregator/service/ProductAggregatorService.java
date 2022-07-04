package com.example.fluxpatterns.gatewayaggregator.service;

import java.util.List;

import com.example.fluxpatterns.gatewayaggregator.client.ProductClient;
import com.example.fluxpatterns.gatewayaggregator.client.PromotionClient;
import com.example.fluxpatterns.gatewayaggregator.client.RewiewClient;
import com.example.fluxpatterns.gatewayaggregator.resource.Price;
import com.example.fluxpatterns.gatewayaggregator.resource.ProductAggregator;
import com.example.fluxpatterns.gatewayaggregator.resource.ProductResource;
import com.example.fluxpatterns.gatewayaggregator.resource.PromotionResource;
import com.example.fluxpatterns.gatewayaggregator.resource.ReviewResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductAggregatorService {
    private final ProductClient productClient;
    private final PromotionClient promotionClient;
    private final RewiewClient rewiewClient;

    public Mono<ProductAggregator> aggregate(int id) {
        return Mono.zip(
                productClient.getById(id),
                promotionClient.getById(id),
                rewiewClient.getById(id))
            .map(objects -> toResource(objects.getT1(), objects.getT2(), objects.getT3()));
    }

    private ProductAggregator toResource(ProductResource product, PromotionResource promotion,
                                         List<ReviewResource> reviews) {
        double amountSaved = product.getPrice() * promotion.getDisCount() / 100;
        double discountPrice = product.getPrice() - amountSaved;

        Price price = Price.builder()
            .amountSaved(amountSaved)
            .discountPrice(discountPrice)
            .listPrice((int) product.getPrice())
            .endDate(promotion.getEndDate())
            .build();

        return ProductAggregator.builder()
            .price(price)
            .id(product.getId())
            .description(product.getDescription())
            .type(product.getType())
            .reviews(reviews)
            .build();
    }
}
