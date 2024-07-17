package com.camunda.project.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Response {
    int code;
    String message;
    long data;
}
