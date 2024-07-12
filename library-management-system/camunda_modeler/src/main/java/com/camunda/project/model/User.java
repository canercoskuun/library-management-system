package com.camunda.project.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
