package com.camunda.project.dto;

import com.camunda.project.model.Book;
import lombok.Data;

@Data
public class BookRequest {
    private long id;
    private String title;
    private String author;
    private String isbn;

    public BookRequest(long id){
        this.id = id;

    }
}
