package com.ecommerce.Ecommerce.api.controllers;

import com.ecommerce.Ecommerce.domain.dtos.products.ProductRequestDTO;
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
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductModel> save(@Valid @RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO updatedProduct) {
        return ResponseEntity.ok(service.update(id, updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
