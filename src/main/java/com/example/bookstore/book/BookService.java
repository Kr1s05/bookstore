package com.example.bookstore.book;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    final
    BookRepository bookRepository;

    @Transactional
    public FilterBookResult getBooksByFilters(FilterObject filterObject) {
        String authorString = null, genreString = null;

        if (filterObject.authors.length != 0) {
            StringBuilder authorStringBuilder = new StringBuilder();
            for (String author :
                    filterObject.authors) {
                authorStringBuilder.append("'");
                authorStringBuilder.append(author);
                authorStringBuilder.append("'");
            }
            authorString = authorStringBuilder.toString();
        }

        if (filterObject.genres.length != 0) {
            StringBuilder genreStringBuilder = new StringBuilder();
            for (String genre :
                    filterObject.genres) {
                genreStringBuilder.append("'");
                genreStringBuilder.append(genre);
                genreStringBuilder.append("'");
            }
            genreString = genreStringBuilder.toString();
        }

        Integer totalCount = 10;
        List<Book> books = bookRepository.filter_books(
                authorString,
                genreString,
                filterObject.minPrice,
                filterObject.maxPrice,
                filterObject.pageNumber,
                filterObject.pageSize,
                totalCount);

        System.out.println(totalCount);
        return new FilterBookResult(BookDTO.bulkConvert(books), totalCount);
    }

    public double maxPrice() {
        return bookRepository.getMaxPrice();
    }

    public double minPrice() {
        return bookRepository.getMinPrice();
    }
}
