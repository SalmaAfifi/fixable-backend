package com.thesis.fixable.exceptionshandling.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistException extends RuntimeException {

    private final HttpStatus status;

    public EmailAlreadyExistException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public EmailAlreadyExistException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
