package com.ecommerce.Ecommerce.domain.dtos.products;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(
            @NotEmpty(message = "product name is required.")
            String name,

            @NotNull(message = "product price is required.")
            @Positive(message = "product price must be greater than zero.")
            BigDecimal price,

            @NotNull(message = "product category is required.")
            @Positive(message = "product category must be greater than zero.")
            Long categoryId
        ) {
}
