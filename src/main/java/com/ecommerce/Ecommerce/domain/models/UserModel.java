package com.ecommerce.Ecommerce.domain.models;

import com.ecommerce.Ecommerce.domain.dtos.auth.AuthRegisterRequestDTO;
import com.ecommerce.Ecommerce.domain.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String role;

    public UserModel(AuthRegisterRequestDTO request) {
        this.username = request.username();
        this.email = request.email();
        this.password = request.password();
        this.role = request.role();
    }
}
