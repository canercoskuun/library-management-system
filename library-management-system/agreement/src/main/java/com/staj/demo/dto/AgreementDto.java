package com.staj.demo.dto;


import com.staj.demo.model.Book;
import com.staj.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreementDto {
    private User user;
    private Book book;
}