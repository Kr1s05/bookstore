package com.example.bookstore.controllers;

import com.example.bookstore.author.Author;
import com.example.bookstore.author.AuthorService;
import com.example.bookstore.book.Book;
import com.example.bookstore.book.BookService;
import com.example.bookstore.genre.Genre;
import com.example.bookstore.genre.GenreService;
import com.example.bookstore.publisher.Publisher;
import com.example.bookstore.publisher.PublisherService;
import com.example.bookstore.services.ImageService;
import com.example.bookstore.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AdminController {
    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final UserService userService;
    private final ImageService imageService;
    private final GenreService genreService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("Publishers", publisherService.getAll());
        model.addAttribute("Authors", authorService.getAll());
        model.addAttribute("Books", bookService.getAll());
        model.addAttribute("Genres", genreService.getAll());
        model.addAttribute("Admins", userService.getAdmins());
        return "admin";
    }

    @PostMapping("/admin/add-book")
    public ResponseEntity<Void> addBook(
            @RequestParam("isbn") String isbn,
            @RequestParam("title") String title,
            @RequestParam("publisherId") int publisherId,
            @RequestParam("publishDate") Date publishDate,
            @RequestParam("author_ids") String authorIds,
            @RequestParam("genre_ids") String genreIds,
            @RequestParam("description") String description,
            @RequestParam("pageCount") int pageCount,
            @RequestParam("price") double price,
            @RequestParam("availableCount") int availableCount,
            @RequestParam("coverImage") MultipartFile coverImage
    ) throws IOException {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setPages(pageCount);
        book.setPublish_date(publishDate);
        book.setQuantity(availableCount);
        book.setDescription(description);
        book.setPrice(price);

        byte[] cover = imageService.resizeToHeight(coverImage, 800);
        book.setCover(cover);

        Publisher publisher = publisherService.getById(publisherId);
        book.setPublisher(publisher);

        List<Integer> genreIdsList = Arrays.stream(genreIds
                        .strip()
                        .replaceFirst("^,", "")
                        .replaceFirst(",$", "")
                        .split(",,"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Set<Genre> genres = genreService.getAllByIds(genreIdsList);
        book.setGenres(genres);

        List<Integer> authorIdsList = Arrays.stream(authorIds
                        .strip()
                        .replaceFirst("^,", "")
                        .replaceFirst(",$", "")
                        .split(",,"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Set<Author> authors = authorService.getAllByIds(authorIdsList);
        book.setAuthors(authors);

        bookService.save(book);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/set-quantity")
    public ResponseEntity<Void> setQuantityById(
            @RequestParam("bookId") int bookId,
            @RequestParam("availableQuantity") int availableQuantity
    ) {
        bookService.updateQuantityById(bookId, availableQuantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/add-author")
    public ResponseEntity<Void> addAuthor(
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        byte[] imageBytes = imageService.resizeToHeight(image, 800);
        Author author = new Author();
        author.setName(name);
        author.setPhoto(imageBytes);

        authorService.save(author);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/add-genre")
    public ResponseEntity<Void> addGenre(
            @RequestParam("name") String name
    ) {
        Genre genre = new Genre();
        genre.setName(name);

        genreService.save(genre);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/add-publisher")
    public ResponseEntity<Void> addPublisher(
            @RequestParam("name") String name
    ) {
        Publisher publisher = new Publisher();
        publisher.setName(name);

        publisherService.save(publisher);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/add-admin")
    public ResponseEntity<Void> addAdmin(
            @RequestParam("username") String username
    ) {
        userService.setAdmin(username);
        return ResponseEntity.ok().build();
    }
}
