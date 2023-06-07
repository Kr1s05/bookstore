package com.example.bookstore.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;


@Data
@AllArgsConstructor
public class Cart {
    HashMap<CartItem, Integer> cart;
    public void addToCart(CartItem c){
        cart.put(c, cart.getOrDefault(c, 1));
    }
}