package com.example.testmp.goods.orders.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class OrderEntityId implements Serializable {

    @Column(name = "good_id")
    private long goodId;

    @Column(name = "seller_id")
    private long sellerId;

    @Column(name = "client_id")
    private long clientId;

}
