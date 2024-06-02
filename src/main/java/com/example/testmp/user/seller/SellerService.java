package com.example.testmp.user.seller;

import com.example.testmp.goods.stock.StockService;
import com.example.testmp.security.model.Authority;
import com.example.testmp.security.service.UserService;
import com.example.testmp.user.data.UserEntity;
import com.example.testmp.user.seller.model.Seller;
import com.example.testmp.user.seller.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final UserService userService;

    private final StockService stockService;

    public List<Seller> getAllSellers() {
        return userService.getUsersByRole(Authority.SELLER).stream().map(UserEntity::getId).map(this::getSeller).toList();
    }


    public Seller getSeller(long sellerId) {
        UserEntity user = userService.getUserById(sellerId);
        Stock stock = stockService.getStock(sellerId);

        return new Seller(user.getId(), user.getLogin(), user.getEmail(), stock);
    }

}
