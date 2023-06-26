package com.example.bookstore.author;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthorService {
    final AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Set<Author> getAllByIds(List<Integer> authorIds) {
        return new HashSet<>(authorRepository.findAllById(authorIds));
    }

    public void save(Author author) {
        authorRepository.save(author);
    }
}
