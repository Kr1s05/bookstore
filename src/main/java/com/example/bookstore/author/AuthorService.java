package com.example.bookstore.author;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    final AuthorRepository authorRepository;
    public List<Author> getAll(){
        return authorRepository.findAll();
    }

}
