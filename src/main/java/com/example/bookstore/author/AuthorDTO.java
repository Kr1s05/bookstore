package com.example.bookstore.author;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthorDTO {
    int id;
    String name;
    String wiki_link;
    String bio;

    public static AuthorDTO convert(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getWiki_link(),
                author.getBio());
    }
}
