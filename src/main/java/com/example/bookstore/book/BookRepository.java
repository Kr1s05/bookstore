package com.example.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Procedure(name = "filter_books_by_author_price")
    List<Book> filterBooksByAuthorPrice(@Param("author_list") String authorList,
                                        @Param("min_price") Double minPrice,
                                        @Param("max_price") Double maxPrice);
}
