package com.example.bookstore.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    final GenreRepository genreRepository;
    public List<Genre> getAll(){
        return genreRepository.findAll();
    }
}
