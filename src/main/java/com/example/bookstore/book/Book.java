package com.example.bookstore.book;

import com.example.bookstore.author.Author;
import com.example.bookstore.genre.Genre;
import com.example.bookstore.publisher.Publisher;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isbn;
    private String title;
    private int pages;
    private Date publish_date;
    private byte[] cover;
    private String description;

    @Column(columnDefinition = "decimal")
    private double price;

    @Column(name = "available")
    private int quantity;

    @ManyToMany
    @JoinTable(name = "book_genre")
    private Set<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "publisher")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "book_author")
    private Set<Author> authors;
}