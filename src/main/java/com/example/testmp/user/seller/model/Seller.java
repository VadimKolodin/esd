package com.example.testmp.user.seller.model;

import com.example.testmp.goods.model.GoodQuantityDto;
import com.example.testmp.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller extends User {

    private Stock stock;

    public Seller(long id, String login, String email, Stock stock) {
        super(id, login, email);
        this.stock = stock;
    }

    public boolean hasInStock(GoodQuantityDto goodQuantityDto) {
        return hasInStock(List.of(goodQuantityDto));
    }

    public boolean hasInStock(List<GoodQuantityDto> goodQuantityDtos) {
        return goodQuantityDtos.stream().allMatch(g -> stock.hasInStock(g));
    }

}
