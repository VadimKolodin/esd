package com.example.testmp.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    private static final String TITLE = "Not found";

    public NotFoundException() {
        title = TITLE;
        message=title;
        status = HttpStatus.NOT_FOUND;
    }

    public NotFoundException(String message) {
        this();
        this.message = message;
    }
}
