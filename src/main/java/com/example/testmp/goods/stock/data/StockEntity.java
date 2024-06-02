package com.example.testmp.goods.stock.data;

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
@Table(name = "goods_stock")
public class StockEntity {


    @EmbeddedId
    private StockEntityId id;

    @Column(name = "quantity")
    private Integer quantity;

}
