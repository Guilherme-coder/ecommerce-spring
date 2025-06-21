package com.ecommerce.Ecommerce.domain.repository;

import com.ecommerce.Ecommerce.domain.models.UserModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findAllByDeletedAtIsNull();

    Optional<UserModel> findByIdAndDeletedAtIsNull(Long id);

    Optional<UserModel> findByUsername(String username);
}
