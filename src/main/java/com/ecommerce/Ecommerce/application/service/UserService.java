package com.ecommerce.Ecommerce.application.service;

import com.ecommerce.Ecommerce.application.security.JwtUtil;
import com.ecommerce.Ecommerce.domain.dtos.auth.AuthRegisterRequestDTO;
import com.ecommerce.Ecommerce.domain.dtos.auth.AuthLoginRequestDTO;
import com.ecommerce.Ecommerce.domain.dtos.auth.AuthLoginResponseDTO;
import com.ecommerce.Ecommerce.domain.models.UserModel;
import com.ecommerce.Ecommerce.domain.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthLoginResponseDTO login(AuthLoginRequestDTO userRequest, AuthenticationManager authManager, JwtUtil jwtUtil){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.username(), userRequest.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);


        return new AuthLoginResponseDTO(token);
    }

    public UserModel register(AuthRegisterRequestDTO userRequest){
        if (repository.findByUsername(userRequest.username()).isPresent()) {
            throw new EntityExistsException("Username already exists");
        }

        String encryptedPassword = passwordEncoder.encode(userRequest.password());

        UserModel newUser = new UserModel();
        newUser.setUsername(userRequest.username());
        newUser.setEmail(userRequest.email());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(userRequest.role());

        return repository.save(newUser);
    }


}
