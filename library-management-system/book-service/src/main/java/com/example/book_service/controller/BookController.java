package com.example.book_service.controller;

import com.example.book_service.dto.BookDto;
import com.example.book_service.model.Book;
import com.example.book_service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookDto bookDto) {
        try {
            return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("update-book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        try {
            Book updatedBook=bookService.updateBook(id, bookDto);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }


    @GetMapping("/get-book-by-title")
    public ResponseEntity<?> getBookByTitle(@RequestParam String title) {
        try {
            return new ResponseEntity<>(bookService.getBookByTitle(title),HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
