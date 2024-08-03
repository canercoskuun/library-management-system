package com.staj.demo.service;

import com.staj.demo.model.Book;
import com.staj.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository;
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

}
