package com.example.testmp.goods.orders;

import com.example.testmp.goods.model.GoodQuantityDto;
import com.example.testmp.goods.orders.data.OrdersRepository;
import com.example.testmp.goods.orders.model.OrderDto;
import com.example.testmp.security.model.Authority;
import com.example.testmp.security.service.UserService;
import com.example.testmp.user.client.model.Cart;
import com.example.testmp.user.data.UserEntity;
import com.example.testmp.user.seller.SellerService;
import com.example.testmp.user.seller.model.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;

    private final OrdersRepository ordersRepository;

    public Cart getCart(long clientId) {
        return new Cart(ordersRepository.getCartByClientId(clientId));
    }

    public List<OrderDto> getOrders() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userService.getUserByLogin(userDetails.getUsername());
        if (user.getRole() == Authority.CLIENT) {
            return getClientsOrders(user.getId());
        } else {
            return getSellersOrders(user.getId());
        }
    }

    private List<OrderDto> getClientsOrders(long clientId) {
        return ordersRepository.getAllByClientId(clientId);
    }

    private List<OrderDto> getSellersOrders(long sellerId) {
        return ordersRepository.getAllBySellerId(sellerId);
    }
}
