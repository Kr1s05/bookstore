package com.example.bookstore.author;

import com.example.bookstore.book.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Author {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column
    private String wikiLink;

    @Column(columnDefinition = "longtext")
    private String bio;

    @Column(columnDefinition = "mediumblob")
    private byte[] photo;

    @ManyToMany(mappedBy = "bookAuthorAuthors")
    private Set<Book> bookAuthorBooks;

}
