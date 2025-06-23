package com.ecommerce.Ecommerce.api.controllers;

import com.ecommerce.Ecommerce.application.security.JwtUtil;
import com.ecommerce.Ecommerce.application.service.UserService;
import com.ecommerce.Ecommerce.domain.dtos.auth.AuthRegisterRequestDTO;
import com.ecommerce.Ecommerce.domain.dtos.auth.AuthLoginRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserService userService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequestDTO request) {
        return ResponseEntity.ok(userService.login(request, authManager, jwtUtil));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRegisterRequestDTO request) {
        return ResponseEntity.ok(userService.register(request));
    }

}
