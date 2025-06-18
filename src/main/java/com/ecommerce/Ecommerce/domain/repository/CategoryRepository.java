package com.ecommerce.Ecommerce.domain.repository;

import com.ecommerce.Ecommerce.domain.models.CategoryModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    List<CategoryModel> findAllByDeletedAtIsNull();

    Optional<CategoryModel> findByIdAndDeletedAtIsNull(Long id);
}
