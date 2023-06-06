package com.example.bookstore.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilterBookResult {
    List<BookDTO> books;
    Integer pageCount;
}
