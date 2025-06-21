package com.ecommerce.Ecommerce.domain.dtos.CartItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemRequestDTO(
        @NotNull(message = "product is required.")
        Long productId,

        @NotNull(message = "quantity is required")
        @Min(value = 1, message = "quantity must be at least 1")
        Integer quantity
) {}
