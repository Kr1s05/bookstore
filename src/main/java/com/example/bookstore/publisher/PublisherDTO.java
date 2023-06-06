package com.example.bookstore.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PublisherDTO {
    int id;
    String name;

    public static PublisherDTO convert(Publisher publisher){
        return new PublisherDTO(publisher.getId(), publisher.getName());
    }
}
