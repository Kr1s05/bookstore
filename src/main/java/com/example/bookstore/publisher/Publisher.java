package com.example.bookstore.publisher;

import com.example.bookstore.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;
}
