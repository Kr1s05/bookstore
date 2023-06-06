package com.example.bookstore.genre;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenreDTO {
    int id;
    String name;

    public static GenreDTO convert(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }
}
