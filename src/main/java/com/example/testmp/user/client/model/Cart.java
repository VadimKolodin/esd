package com.example.testmp.user.client.model;

import com.example.testmp.goods.model.GoodQuantityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private List<GoodQuantityDto> cart;

}
