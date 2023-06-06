package com.example.bookstore.book;

import lombok.Data;

@Data
public class FilterObject {
    String[] authors;
    String[] genres;
    double minPrice;
    double maxPrice;
    int pageNumber;
    int pageSize;
}
