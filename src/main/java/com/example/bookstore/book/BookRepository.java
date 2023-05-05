package com.example.bookstore.book;

import com.example.bookstore.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface BookRepository extends JpaRepository<Book, Integer> {
}
