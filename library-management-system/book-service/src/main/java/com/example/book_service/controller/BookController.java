package com.example.book_service.controller;

import com.example.book_service.dto.BookDto;
import com.example.book_service.model.Book;
import com.example.book_service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookDto bookDto) {
        try {
            bookService.addBook(bookDto);
            return ResponseEntity.ok("Book created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("update-book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        try {
            bookService.updateBook(id, bookDto);
            return ResponseEntity.ok("Book updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }


    @GetMapping("/get-book-by-title")
    public ResponseEntity<?> getBookByTitle(@RequestParam String title) {
        try {
            return ResponseEntity.ok(bookService.getBookByTitle(title));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.getBookById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
