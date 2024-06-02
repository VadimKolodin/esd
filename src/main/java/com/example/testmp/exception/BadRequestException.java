package com.example.testmp.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    private static final String TITLE = "Request error";

    public BadRequestException() {
        title = TITLE;
        message = title;
        status = HttpStatus.BAD_REQUEST;
    }

    public BadRequestException(String message) {
        this();
        this.message = message;
    }
}
