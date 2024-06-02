package com.example.testmp.goods.orders;

import com.example.testmp.exception.BadRequestException;
import com.example.testmp.exception.ForbiddenException;
import com.example.testmp.goods.data.GoodsRepository;
import com.example.testmp.goods.model.GoodQuantityDto;
import com.example.testmp.goods.orders.data.OrderEntity;
import com.example.testmp.goods.orders.data.OrdersRepository;
import com.example.testmp.goods.orders.model.OrderDto;
import com.example.testmp.security.model.Authority;
import com.example.testmp.security.service.UserService;
import com.example.testmp.user.client.ClientService;
import com.example.testmp.user.client.model.Cart;
import com.example.testmp.user.client.model.Client;
import com.example.testmp.user.data.UserEntity;
import com.example.testmp.user.seller.SellerService;
import com.example.testmp.user.seller.model.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final SellerService sellerService;

    private final ClientService clientService;

    private final UserService userService;

    private final OrdersRepository ordersRepository;

    private final GoodsRepository goodsRepository;

    @Transactional
    public OrderDto createOrder(long goodId, int quantity) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userService.getUserByLogin(userDetails.getUsername());
        if (user.getRole() != Authority.CLIENT) {
            throw new ForbiddenException("Only clients can create orders");
        }
        Client client = clientService.getClientByLogin(userDetails.getUsername());
        Seller seller = sellerService.getAllSellers()
                                     .stream()
                                     .filter(s -> s.hasInStock(new GoodQuantityDto(goodId, quantity)))
                                     .findAny()
                                     .orElseThrow(() -> new BadRequestException("None can sell this quantity of good"));
        int price = goodsRepository.findPriceById(goodId);
        if (client.getBalance() == null || price * quantity > client.getBalance()) {
            throw new BadRequestException("Client don`t have enough balance");
        }
        ordersRepository.persist(goodId, seller.getId(), client.getId(), quantity);

        return new OrderDto(goodId, seller.getId(), client.getId(), quantity);
    }

}
