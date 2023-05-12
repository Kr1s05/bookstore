package com.example.bookstore.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    final
    BookRepository bookRepository;
    public List<Book> getBooksByAuthorsAndPrice(String authors,double minPrice, double maxPrice){
        String[] authorArray = authors.split(",");
        StringBuilder builder = new StringBuilder();
        for (String s : authorArray) {
            builder.append("'");
            builder.append(s);
            builder.append("',");
        }
        String authorList = builder.toString();
        return bookRepository.filterBooksByAuthorPrice(authorList,minPrice,maxPrice);
    }
}
