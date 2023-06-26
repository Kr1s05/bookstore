package com.example.bookstore.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class GenreService {
    final GenreRepository genreRepository;

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    public Set<Genre> getAllByIds(List<Integer> ids) {
        return new HashSet<>(genreRepository.findAllById(ids));
    }

    public void save(Genre genre) {
        genreRepository.save(genre);
    }
}
