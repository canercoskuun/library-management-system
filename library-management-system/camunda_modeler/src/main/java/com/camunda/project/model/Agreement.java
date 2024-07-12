package com.camunda.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class Agreement {
    private Long id;
    private User user;
    private Book book;
    private Date borrowDate;
    private Date returnDate;
    private String status;

}
