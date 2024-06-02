package com.example.testmp.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {

    private static final String TITLE = "Forbidden";

    public ForbiddenException() {
        title = TITLE;
        message = title;
        status = HttpStatus.FORBIDDEN;
    }

    public ForbiddenException(String message) {
        this();
        this.message=message;
    }
}
