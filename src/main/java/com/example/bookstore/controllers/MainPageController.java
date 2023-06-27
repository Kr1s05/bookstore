package com.example.bookstore.controllers;

import com.example.bookstore.author.AuthorService;
import com.example.bookstore.book.BookDTO;
import com.example.bookstore.book.BookService;
import com.example.bookstore.book.FilterBookResult;
import com.example.bookstore.book.FilterObject;
import com.example.bookstore.cart.Cart;
import com.example.bookstore.cart.CartItem;
import com.example.bookstore.genre.GenreService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@AllArgsConstructor
public class MainPageController {
    final AuthorService authorService;
    final GenreService genreService;
    final BookService bookService;

    @GetMapping("/store")
    public String mainPage(Model model, HttpSession session) {
        if (session.getAttribute("cart") == null) session.setAttribute("cart", new Cart(new HashMap<>()));
        model.addAttribute("Authors", authorService.getAll());
        model.addAttribute("Genres", genreService.getAll());
        model.addAttribute("MaxPrice", bookService.maxPrice());
        model.addAttribute("MinPrice", bookService.minPrice());
        return "/main";
    }

    @PostMapping("/books")
    public String books(@RequestBody FilterObject filters, Model model) {
        FilterBookResult result = bookService.getBooksByFilters(filters);
        model.addAttribute("bookList", result.getBooks());
        model.addAttribute("pageNumber", filters.getPageNumber());
        model.addAttribute("pageCount", result.getPageCount());
        return "/books";
    }

    @ResponseBody
    @GetMapping("/cart/add/{bookId}")
    public Cart buyBook(@PathVariable int bookId, @SessionAttribute Cart cart) {
        CartItem cartItem = CartItem.BookToCartItem(bookService.getBookById(bookId));
        cart.addToCart(cartItem);
        return cart;
    }

    @ResponseBody
    @GetMapping("/cart/remove/{bookId}")
    public Cart trashBook(@PathVariable int bookId, @SessionAttribute Cart cart) {
        CartItem cartItem = CartItem.BookToCartItem(bookService.getBookById(bookId));
        cart.removeFromCart(cartItem);
        return cart;
    }

    @ResponseBody
    @GetMapping("/cart")
    public Cart getCart(@SessionAttribute Cart cart){
        return cart;
    }
}
