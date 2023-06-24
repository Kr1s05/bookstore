package com.example.bookstore.publisher;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherService {
    final PublisherRepository repository;

    public List<Publisher> getAll(){
        return repository.findAll();
    }
    public Publisher getById(int id){
        return repository.findById(id).orElseThrow();
    }

    public void save(Publisher publisher) {
        repository.save(publisher);
    }
}
