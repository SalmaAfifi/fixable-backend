package com.thesis.fixable.exceptionshandling.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public EntityNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
