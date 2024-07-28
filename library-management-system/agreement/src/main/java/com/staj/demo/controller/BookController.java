package com.staj.demo.controller;

import com.staj.demo.model.Book;
import com.staj.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.ok("Book created successfully");
    }


}
