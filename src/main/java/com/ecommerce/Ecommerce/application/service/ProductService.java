package com.ecommerce.Ecommerce.application.service;

import com.ecommerce.Ecommerce.domain.dtos.products.ProductRequestDTO;
import com.ecommerce.Ecommerce.domain.models.CategoryModel;
import com.ecommerce.Ecommerce.domain.models.ProductModel;
import com.ecommerce.Ecommerce.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<ProductModel> findAll() {
        return productRepository.findAllByDeletedAtIsNull();
    }

    public ProductModel findById(Long id) {
        return productRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("product not found."));
    }

    public ProductModel save(ProductRequestDTO productRequest) {
        CategoryModel category = categoryService.findById(productRequest.categoryId());

        ProductModel product = new ProductModel();
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setCategory(category);

        return productRepository.save(product);
    }

    public ProductModel update(Long id, ProductRequestDTO productRequest) {
        CategoryModel category = categoryService.findById(productRequest.categoryId());

        ProductModel product = findById(id);
        product.setId(id);
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setCategory(category);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        ProductModel product = findById(id);

        product.markAsDeleted();
        productRepository.save(product);
    }

    public void destroy(Long id) {
        productRepository.deleteById(id);
    }
}
