package com.camunda.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgreementRequest {
    private long user_id;
    private long book_id;
    private Date borrowDate;
    private Date returnDate;
}
