package com.ecommerce.Ecommerce.api.controllers;

import com.ecommerce.Ecommerce.application.service.CategoryService;
import com.ecommerce.Ecommerce.domain.dtos.category.CategoryRequestDTO;
import com.ecommerce.Ecommerce.domain.models.CategoryModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryModel> listAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> find(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryModel> save(@Valid @RequestBody CategoryRequestDTO Category) {
        return ResponseEntity.ok(service.save(Category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO updatedCategory) {
        return service.findById(id)
                .map(existing -> {
                    updatedCategory.toEntity().setId(id);
                    return ResponseEntity.ok(service.save(updatedCategory));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(category -> {
                    service.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
