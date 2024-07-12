package com.camunda.project.controller;

import com.camunda.project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/model/book")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}