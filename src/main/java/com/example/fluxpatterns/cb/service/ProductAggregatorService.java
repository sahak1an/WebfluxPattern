package com.example.fluxpatterns.cb.service;


import com.example.fluxpatterns.cb.client.ProductClient;
import com.example.fluxpatterns.cb.client.ReviewClient;
import com.example.fluxpatterns.cb.dto.Product;
import com.example.fluxpatterns.cb.dto.ProductAggregate;
import com.example.fluxpatterns.cb.dto.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAggregatorService {
    private final ProductClient productClient;
    private final ReviewClient reviewClient;

    public Mono<ProductAggregate> aggregate(Integer id){
        return Mono.zip(
               this.productClient.getProduct(id),
               this.reviewClient.getReviews(id)
        )
        .map(t -> toDto(t.getT1(), t.getT2()));
    }

    private ProductAggregate toDto(Product product, List<Review> reviews){
        return ProductAggregate.create(
                product.getId(),
                product.getCategory(),
                product.getDescription(),
                reviews
        );
    }


}
