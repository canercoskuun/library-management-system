package com.camunda.project.dto;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public UserRequest(long id){
        this.id = id;
    }
}
