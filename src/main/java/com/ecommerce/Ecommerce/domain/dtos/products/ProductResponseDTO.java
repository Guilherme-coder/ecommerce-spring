package com.ecommerce.Ecommerce.domain.dtos.products;

import com.ecommerce.Ecommerce.domain.models.ProductModel;

public record ProductResponseDTO(
        Long id,
        String name,
        Float price,
        Long categoryId
) {
    public static ProductResponseDTO fromEntity(ProductModel product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getId()
        );
    }
}
