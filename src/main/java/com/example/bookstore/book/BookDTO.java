package com.example.bookstore.book;

import com.example.bookstore.author.AuthorDTO;
import com.example.bookstore.genre.GenreDTO;
import com.example.bookstore.publisher.PublisherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Data
public class BookDTO {
    int id;
    String isbn;
    String title;
    int pages;
    String cover64;
    String description;
    double price;
    int quantity;
    Set<GenreDTO> genres;
    PublisherDTO publisher;
    Set<AuthorDTO> authors;


    public static BookDTO convert(Book book) {
        Set<GenreDTO> genreDTOS = new HashSet<>();
        book.getGenres().forEach(x -> genreDTOS.add(GenreDTO.convert(x)));
        Set<AuthorDTO> authorDTOS = new HashSet<>();
        book.getAuthors().forEach(x -> authorDTOS.add(AuthorDTO.convert(x)));

        return new BookDTO(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getPages(),
                book.getCoverAsBase64(),
                book.getDescription(),
                book.getPrice(),
                book.getQuantity(),
                genreDTOS,
                PublisherDTO.convert(book.getPublisher()),
                authorDTOS
        );
    }

    public static List<BookDTO> bulkConvert(List<Book> books) {
        List<BookDTO> dtos = new LinkedList<>();
        books.forEach(book -> dtos.add(BookDTO.convert(book)));
        return dtos;
    }
}
