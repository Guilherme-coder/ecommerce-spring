package com.ecommerce.Ecommerce.application.service;

import com.ecommerce.Ecommerce.domain.dtos.category.CategoryRequestDTO;
import com.ecommerce.Ecommerce.domain.models.CategoryModel;
import com.ecommerce.Ecommerce.domain.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<CategoryModel> findAll() {
        return repository.findAllByDeletedAtIsNull();
    }

    public CategoryModel findById(Long id) {
        return repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("category not found"));
    }

    public CategoryModel save(CategoryRequestDTO categoryRequest) {
        return repository.save(categoryRequest.toEntity());
    }

    public CategoryModel update(Long id, CategoryRequestDTO categoryRequest) {
        CategoryModel category = findById(id);
        category.setName(categoryRequest.name());

        return repository.save(category);
    }

    public void delete(Long id) {
        CategoryModel category = findById(id);

        category.markAsDeleted();
        repository.save(category);
    }

    public void destroy(Long id) {
        repository.deleteById(id);
    }
}
