package com.example.testmp.user.client.model;

import com.example.testmp.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {


    private Integer balance;

    private Cart cart;

    public Client(long id, String login, String email, Integer balance, Cart cart) {
        super(id, login, email);
        this.balance = balance;
        this.cart = cart;
    }

}
