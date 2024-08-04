package com.example.book_service.dto;


import lombok.Data;

@Data
public class BookDto {

    private String title;

    private String author;

    private String isbn;

    private int quantity;

    private boolean availability;
    public boolean getAvailability() {
        return availability;
    }


}
