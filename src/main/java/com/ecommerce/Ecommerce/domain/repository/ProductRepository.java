package com.ecommerce.Ecommerce.domain.repository;

import com.ecommerce.Ecommerce.domain.models.ProductModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findAllByDeletedAtIsNull();

    Optional<ProductModel> findByIdAndDeletedAtIsNull(Long id);
}
