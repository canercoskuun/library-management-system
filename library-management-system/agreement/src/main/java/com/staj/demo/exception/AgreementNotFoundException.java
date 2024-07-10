package com.staj.demo.exception;

public class AgreementNotFoundException extends RuntimeException{
    public AgreementNotFoundException(String msg) {
        super(msg);
    }
}
