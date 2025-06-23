package com.ecommerce.Ecommerce.domain.repository;

import com.ecommerce.Ecommerce.domain.dtos.auth.AuthRegisterRequestDTO;
import com.ecommerce.Ecommerce.domain.models.UserModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User by user name")
    void findByUsernameSuccess() {
        String username = "arlindo";
        AuthRegisterRequestDTO user = new AuthRegisterRequestDTO(
                "arlindo",
                "arlindo@teste.com",
                "password",
                "admin"
        );

        this.createUser(user);
        Optional<UserModel> result = this.userRepository.findByUsername(username);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User by user name")
    void findByUsernameFailure() {
        String username = "marco";
        AuthRegisterRequestDTO user = new AuthRegisterRequestDTO(
                "arlindoteste",
                "arlindo@teste.com",
                "password",
                "admin"
        );

        this.createUser(user);
        Optional<UserModel> result = this.userRepository.findByUsername(username);

        assertThat(result.isPresent()).isFalse();
    }

    private UserModel createUser(AuthRegisterRequestDTO request){
        UserModel user = new UserModel(request);
        this.entityManager.persist(user);
        return user;
    }
}