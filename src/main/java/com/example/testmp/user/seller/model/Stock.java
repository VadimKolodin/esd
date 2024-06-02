package com.example.testmp.user.seller.model;

import com.example.testmp.goods.model.GoodQuantityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    private List<GoodQuantityDto> stock;

    public boolean hasInStock(GoodQuantityDto goodQuantityDto) {
        return stock.stream()
                    .filter(g -> Objects.equals(g.getGoodId(), goodQuantityDto.getGoodId()))
                    .filter(g -> g.getQuantity() >= goodQuantityDto.getQuantity())
                    .toList()
                    .size() > 0;
    }

}
