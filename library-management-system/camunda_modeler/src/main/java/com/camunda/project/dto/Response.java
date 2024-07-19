package com.camunda.project.dto;

import lombok.Data;

@Data
public class Response {
    int code;
    String message;
    String data;

    public Response(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
