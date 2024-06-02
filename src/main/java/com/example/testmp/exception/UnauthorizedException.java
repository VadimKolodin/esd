package com.example.testmp.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {

    private static final String TITLE = "Unauthorized";

    public UnauthorizedException() {
        title = TITLE;
        message = title;
        status = HttpStatus.UNAUTHORIZED;
    }

    public UnauthorizedException(String message) {
        this();
        this.message = message;
    }
}
