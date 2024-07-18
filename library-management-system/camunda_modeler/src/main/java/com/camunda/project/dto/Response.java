package com.camunda.project.dto;


public class Response {
    int code;
    String message;
    long data;

    public Response(int code, String message, long data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
