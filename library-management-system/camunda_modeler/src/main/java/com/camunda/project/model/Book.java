package com.camunda.project.model;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
}
