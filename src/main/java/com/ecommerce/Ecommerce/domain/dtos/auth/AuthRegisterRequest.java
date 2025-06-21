package com.ecommerce.Ecommerce.domain.dtos.auth;

import jakarta.validation.constraints.NotEmpty;

public record AuthRegisterRequest(
        @NotEmpty(message = "username is required.")
        String username,

        @NotEmpty(message = "password is required.")
        String password,

        @NotEmpty(message = "role is required.")
        String role
) {}
