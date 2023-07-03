package com.example.bookstore.author;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Base64;

@Data
@AllArgsConstructor
public class AuthorDTO {
    int id;
    String name;
    String bio;
    String wikiLink;
    byte[] photo;

    public static AuthorDTO convert(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getBio(),
                author.getWiki_link(),
                author.getPhoto());
    }

    public String getPhotoAsBase64() {
        return Base64.getEncoder().encodeToString(photo);
    }

}
