package com.staj.demo.service;

import com.staj.demo.model.Book;
import com.staj.demo.model.User;
import com.staj.demo.repository.BookRepository;
import com.staj.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository;
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

}
