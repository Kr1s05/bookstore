package com.example.bookstore.author;

import com.example.bookstore.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
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
