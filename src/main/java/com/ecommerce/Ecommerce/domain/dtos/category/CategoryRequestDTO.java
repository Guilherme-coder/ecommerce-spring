package com.ecommerce.Ecommerce.domain.dtos.category;

import com.ecommerce.Ecommerce.domain.models.CategoryModel;
import jakarta.validation.constraints.NotEmpty;

public record CategoryRequestDTO(
        @NotEmpty(message = "category name is required.")
        String name
) {

    public CategoryModel toEntity() {
        CategoryModel entity = new CategoryModel();
        entity.setName(name);
        return entity;
    }
}