package com.example.bookstore.author;

import com.example.bookstore.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String wiki_link;
    private String bio;
    private byte[] photo;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
