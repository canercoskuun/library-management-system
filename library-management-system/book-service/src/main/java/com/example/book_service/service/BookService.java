package com.example.book_service.service;

import com.example.book_service.dto.BookDto;
import com.example.book_service.model.Book;
import com.example.book_service.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository;
    public Book addBook(BookDto bookDto) {
        Book book=new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setQuantity(bookDto.getQuantity());
        book.setAvailability(true);
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setQuantity(bookDto.getQuantity());
        book.setAvailability(bookDto.getAvailability());
        bookRepository.save(book);
    }
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
}