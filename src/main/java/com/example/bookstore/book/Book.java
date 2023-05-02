package com.example.bookstore.book;

import com.example.bookstore.author.Author;
import com.example.bookstore.genre.Genre;
import com.example.bookstore.link.Link;
import com.example.bookstore.publisher.Publisher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Book {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column
    private Integer pages;

    @Column
    private LocalDate publishDate;

    @Column(length = 200)
    private String bookFormat;

    @Column(columnDefinition = "blob")
    private byte[] cover;

    @Column(name = "\"description\"", columnDefinition = "longtext")
    private String description;

    @Column(precision = 8, scale = 2)
    private BigDecimal price;

    @Column
    private Boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> bookAuthorAuthors;

    @OneToMany(mappedBy = "book")
    private Set<Link> bookLinks;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> bookGenreGenres;

}
