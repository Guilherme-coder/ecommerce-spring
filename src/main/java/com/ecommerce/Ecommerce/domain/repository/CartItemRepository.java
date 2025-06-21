package com.ecommerce.Ecommerce.domain.repository;

import com.ecommerce.Ecommerce.domain.models.CartItemModel;
import com.ecommerce.Ecommerce.domain.models.ProductModel;
import com.ecommerce.Ecommerce.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {
    List<CartItemModel> findByUser(UserModel user);
    Optional<CartItemModel> findByUserAndProduct(UserModel user, ProductModel product);
    void deleteByUserAndProduct(UserModel user, ProductModel product);
}
