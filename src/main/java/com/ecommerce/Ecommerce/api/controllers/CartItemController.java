package com.ecommerce.Ecommerce.api.controllers;

import com.ecommerce.Ecommerce.application.security.UserDetailsServiceImpl;
import com.ecommerce.Ecommerce.application.service.CartItemService;
import com.ecommerce.Ecommerce.domain.dtos.CartItem.CartItemRequestDTO;
import com.ecommerce.Ecommerce.domain.dtos.CartItem.CartItemResponseDTO;
import com.ecommerce.Ecommerce.domain.models.UserModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartService;
    private final UserDetailsServiceImpl userService;


    @GetMapping
    public ResponseEntity<List<CartItemResponseDTO>> listAll() {
        UserModel user = userService.getLoggedUser();
        List<CartItemResponseDTO> items = cartService.getCart(user).stream().map(item ->
                new CartItemResponseDTO(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getProduct().getPrice(),
                        item.getSubtotal()
                )
        ).toList();

        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CartItemRequestDTO request) {
        UserModel user = userService.getLoggedUser();
        cartService.addToCart(user, request.productId(), request.quantity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> remove(@PathVariable Long productId) {
        UserModel user = userService.getLoggedUser();
        cartService.removeFromCart(user, productId);
        return ResponseEntity.ok().build();
    }
}
