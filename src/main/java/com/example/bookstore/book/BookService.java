package com.example.bookstore.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    final
    BookRepository bookRepository;
    public List<Book> getBooksByFilters(FilterObject filterObject){
       StringBuilder authorStringBuilder = new StringBuilder();
        for (String author:
                filterObject.authors) {
            authorStringBuilder.append("\'");
            authorStringBuilder.append(author);
            authorStringBuilder.append("\'");
        }
        String authorString = authorStringBuilder.toString();
        StringBuilder genreStringBuilder = new StringBuilder();
        for (String genre:
                filterObject.genres) {
            genreStringBuilder.append("\'");
            genreStringBuilder.append(genre);
            genreStringBuilder.append("\'");
        }
        String genreString = authorStringBuilder.toString();
        return bookRepository.filterBooks(authorString, genreString, filterObject.minPrice, filterObject.maxPrice);
    }
    public double maxPrice(){
        return bookRepository.getMaxPrice();
    }
    public double minPrice(){
        return bookRepository.getMinPrice();
    }
}
