package com.example.bookstore.controllers;

import com.example.bookstore.author.AuthorService;
import com.example.bookstore.book.BookService;
import com.example.bookstore.book.FilterBookResult;
import com.example.bookstore.book.FilterObject;
import com.example.bookstore.genre.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class MainPageController {
    final AuthorService authorService;
    final GenreService genreService;
    final BookService bookService;

    @GetMapping("/store")
    public String mainPage(Model model){
        model.addAttribute("Authors",authorService.getAll());
        model.addAttribute("Genres",genreService.getAll());
        model.addAttribute("MaxPrice",bookService.maxPrice());
        model.addAttribute("MinPrice",bookService.minPrice());
        return "/main";
    }
    @PostMapping("/books")
    public String books(@RequestBody FilterObject filters, Model model){
        FilterBookResult result = bookService.getBooksByFilters(filters);
        model.addAttribute("bookList", result.getBooks());
        model.addAttribute("pageNumber", filters.getPageNumber());
        model.addAttribute("pageCount", result.getPageCount());
        return "/books";
    }
}
