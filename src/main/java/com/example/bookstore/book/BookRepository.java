package com.example.bookstore.book;

import groovyjarjarantlr4.v4.misc.MutableInt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.SqlOutParameter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Procedure(name = "filter_books", value = "filter_books")
    List<Book> filter_books(
            @Param("author_list") String authorList,
            @Param("genre_list") String genreList,
            @Param("min_price") Double minPrice,
            @Param("max_price") Double maxPrice,
            @Param("page_number") int pageNumber,
            @Param("page_size") int pageSize,
            @Param("total_count") Integer totalCount
    );

    @Query(value = "select MAX(b.price) from Book b")
    double getMaxPrice();

    @Query(value = "select MIN(b.price) from Book b")
    double getMinPrice();
}
