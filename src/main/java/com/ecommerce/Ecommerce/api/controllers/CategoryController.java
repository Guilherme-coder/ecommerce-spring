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
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryModel> save(@Valid @RequestBody CategoryRequestDTO category) {
        return ResponseEntity.ok(service.save(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO category) {

        return ResponseEntity.ok(service.update(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
