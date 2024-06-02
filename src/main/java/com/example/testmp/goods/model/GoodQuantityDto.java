package com.example.testmp.goods.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodQuantityDto {

    private long goodId;

    private int quantity;

}
