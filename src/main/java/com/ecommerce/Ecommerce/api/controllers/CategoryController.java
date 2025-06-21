package com.ecommerce.Ecommerce.api.controllers;

import com.ecommerce.Ecommerce.application.service.CategoryService;
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
    public ResponseEntity<CategoryModel> save(@Valid @RequestBody CategoryModel Category) {
        return ResponseEntity.ok(service.save(Category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable Long id, @Valid @RequestBody CategoryModel updatedCategory) {
        return service.findById(id)
                .map(existing -> {
                    updatedCategory.setId(id);
                    return ResponseEntity.ok(service.save(updatedCategory));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(Category -> {
                    service.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
