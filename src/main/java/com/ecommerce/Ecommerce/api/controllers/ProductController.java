package com.ecommerce.Ecommerce.api.controllers;

import com.ecommerce.Ecommerce.domain.dtos.products.ProductResponseDTO;
import com.ecommerce.Ecommerce.application.service.ProductService;
import com.ecommerce.Ecommerce.domain.models.ProductModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponseDTO> listAll(){
        return service.findAll().stream()
                .map(ProductResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> find(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductModel> save(@Valid @RequestBody ProductModel product) {
        return ResponseEntity.ok(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable Long id, @Valid @RequestBody ProductModel updatedProduct) {
        return service.findById(id)
                .map(existing -> {
                    updatedProduct.setId(id);
                    return ResponseEntity.ok(service.save(updatedProduct));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(product -> {
                    service.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
