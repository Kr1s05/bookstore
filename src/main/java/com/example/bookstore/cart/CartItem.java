package com.example.bookstore.cart;

import com.example.bookstore.book.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    int id;
    String title;
    double price;
    public static CartItem BookToCartItem(BookDTO book){
        return new CartItem(book.getId(), book.getTitle(), book.getPrice());
    }
}
