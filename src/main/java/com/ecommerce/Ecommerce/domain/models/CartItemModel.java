package com.ecommerce.Ecommerce.domain.models;

import com.ecommerce.Ecommerce.domain.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_cart_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;

    @ManyToOne
    private ProductModel product;

    private int quantity;

    public BigDecimal getSubtotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
