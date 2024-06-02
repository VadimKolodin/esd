package com.example.testmp.user.client;

import com.example.testmp.exception.NotFoundException;
import com.example.testmp.goods.orders.OrderService;
import com.example.testmp.security.service.UserService;
import com.example.testmp.user.client.model.Cart;
import com.example.testmp.user.client.model.Client;
import com.example.testmp.user.data.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final UserService userService;

    private final OrderService orderService;

    public Client getClientByLogin(String login) {
        UserEntity user = Optional.ofNullable(userService.getUserByLogin(login)).orElseThrow(() -> new NotFoundException("Client is not found"));
        Cart cart = orderService.getCart(user.getId());

        return new Client(user.getId(), user.getLogin(), user.getEmail(), user.getBalance(), cart);
    }

}
