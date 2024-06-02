package com.example.testmp.goods.stock.data;

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
public class StockEntityId implements Serializable {

    @Column(name = "good_id")
    private long goodId;

    @Column(name = "seller_id")
    private long sellerId;

}
