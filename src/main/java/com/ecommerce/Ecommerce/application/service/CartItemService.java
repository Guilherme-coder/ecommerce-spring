package com.ecommerce.Ecommerce.application.service;

import com.ecommerce.Ecommerce.domain.models.CartItemModel;
import com.ecommerce.Ecommerce.domain.models.ProductModel;
import com.ecommerce.Ecommerce.domain.models.UserModel;
import com.ecommerce.Ecommerce.domain.repository.CartItemRepository;
import com.ecommerce.Ecommerce.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public List<CartItemModel> getCart(UserModel user) {
        return cartItemRepository.findByUser(user);
    }

    public void addToCart(UserModel user, Long productId, int quantity) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("product not found."));

        CartItemModel item = cartItemRepository.findByUserAndProduct(user, product)
                .orElse(new CartItemModel());

        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(item.getId() == null ? quantity : item.getQuantity() + quantity);

        cartItemRepository.save(item);
    }

    public void removeFromCart(UserModel user, Long productId) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("product not found."));

        cartItemRepository.deleteByUserAndProduct(user, product);
    }
}
