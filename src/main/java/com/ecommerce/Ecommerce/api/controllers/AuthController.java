package com.ecommerce.Ecommerce.api.controllers;

import com.ecommerce.Ecommerce.application.security.JwtUtil;
import com.ecommerce.Ecommerce.domain.dtos.auth.AuthRegisterRequest;
import com.ecommerce.Ecommerce.domain.dtos.auth.AuthRequest;
import com.ecommerce.Ecommerce.domain.dtos.auth.AuthResponse;
import com.ecommerce.Ecommerce.domain.models.UserModel;
import com.ecommerce.Ecommerce.domain.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            return ResponseEntity.badRequest().body("user already exists.");
        }

        String encryptedPassword = passwordEncoder.encode(request.password());

        UserModel newUser = new UserModel();
        newUser.setUsername(request.username());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(request.role());

        userRepository.save(newUser);

        return ResponseEntity.ok("user successfully register.");
    }

}
