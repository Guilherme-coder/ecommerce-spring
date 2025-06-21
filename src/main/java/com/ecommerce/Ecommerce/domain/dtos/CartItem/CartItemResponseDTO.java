package com.ecommerce.Ecommerce.domain.dtos.CartItem;

import java.math.BigDecimal;

public record CartItemResponseDTO(String productName, int quantity, BigDecimal unitPrice, BigDecimal subtotal) {}
