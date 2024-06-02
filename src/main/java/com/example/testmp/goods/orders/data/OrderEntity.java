package com.example.testmp.goods.orders.data;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "goods_orders")
public class OrderEntity {

    @EmbeddedId
    private OrderEntityId id;

    @Column(name = "quantity")
    private Integer quantity;

}
