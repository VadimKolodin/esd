package com.example.testmp.goods.orders.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private long goodId;

    private long sellerId;

    private long clientId;

    private Integer quantity;
}
