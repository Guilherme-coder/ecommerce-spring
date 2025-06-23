package com.ecommerce.Ecommerce.application.service;

import com.ecommerce.Ecommerce.domain.dtos.category.CategoryRequestDTO;
import com.ecommerce.Ecommerce.domain.models.CategoryModel;
import com.ecommerce.Ecommerce.domain.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<CategoryModel> findAll() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<CategoryModel> findById(Long id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public CategoryModel save(CategoryRequestDTO category) {
        return repository.save(category.toEntity());
    }

    public void delete(Long id) {
        CategoryModel Category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found."));

        Category.markAsDeleted();
        repository.save(Category);
    }

    public void destroy(Long id) {
        repository.deleteById(id);
    }
}
