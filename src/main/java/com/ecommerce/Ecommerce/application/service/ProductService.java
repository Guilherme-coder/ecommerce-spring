package com.ecommerce.Ecommerce.application.service;

import com.ecommerce.Ecommerce.domain.models.ProductModel;
import com.ecommerce.Ecommerce.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductModel> findAll() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<ProductModel> findById(Long id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public ProductModel save(ProductModel product) {
        return repository.save(product);
    }

    public void delete(Long id) {
        ProductModel product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found."));

        product.markAsDeleted();
        repository.save(product);
    }

    public void destroy(Long id) {
        repository.deleteById(id);
    }
}
