package com.example.testmp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {

    protected HttpStatus status;

    protected String title;

    protected String message;

    public ApiException() {
    }

    public ApiException(String message) {
        super();
        this.message=message;
    }

    public ApiExceptionMessage getApiMessage() {
        return new ApiExceptionMessage(title, message);
    }

    @Data
    @AllArgsConstructor
    public class ApiExceptionMessage {

        protected String title;

        protected String message;
    }
}
