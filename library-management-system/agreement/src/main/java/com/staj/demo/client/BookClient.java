package com.staj.demo.client;


import com.staj.demo.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BookClient {


    private final WebClient webClient;

    public BookClient(WebClient webClient) {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8086/api/books").build();
    }


    public Book getBookById(Long bookId) {
        return webClient.get()
                .uri("/get/" + bookId)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }
    public void updateBook(Book book) {
        webClient.put()
                .uri("/update-book/" + book.getId())
                .headers(headers -> headers.setBasicAuth("admin", "admin"))
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

}
