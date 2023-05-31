package com.example.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Procedure(name = "filter_books")
    List<Book> filterBooks(@Param("author_list") String authorList,
                                        @Param("genre_list") String genreList,
                                        @Param("min_price") Double minPrice,
                                        @Param("max_price") Double maxPrice);

    @Query(value = "select MAX(b.price) from Book b")
    double getMaxPrice();

    @Query(value = "select MIN(b.price) from Book b")
    double getMinPrice();
}
