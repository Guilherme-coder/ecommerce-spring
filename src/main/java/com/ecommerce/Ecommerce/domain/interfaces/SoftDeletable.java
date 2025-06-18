package com.ecommerce.Ecommerce.domain.interfaces;

import java.time.LocalDateTime;

public interface SoftDeletable {
    LocalDateTime getDeletedAt();
    void setDeletedAt(LocalDateTime deletedAt);

    default boolean isDeleted() {
        return getDeletedAt() != null;
    }

    default void markAsDeleted() {
        setDeletedAt(LocalDateTime.now());
    }
}
