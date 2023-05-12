package com.example.bookstore.book;

import com.example.bookstore.author.Author;
import com.example.bookstore.genre.Genre;
import com.example.bookstore.publisher.Publisher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isbn;
    private String title;
    private int pages;
    private Date publish_date;

    private byte[] cover;
    public String getCoverAsBase64(){
        return Base64.getEncoder().encodeToString(cover);
    }

    private String description;

    @Column(columnDefinition = "decimal")
    private double price;

    @Column(name = "available")
    private int quantity;

    @ManyToMany
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
        )
    private Set<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "publisher")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
}