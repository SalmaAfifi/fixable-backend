package com.thesis.fixable.exceptionshandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiError {

    @JsonProperty
    private HttpStatus status;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    @JsonProperty
    private String message;
    @JsonProperty
    private String debugMessage;
    @JsonProperty
    private List<? extends ApiSubError> subErrors;


    public ApiError() {
    }

    public ApiError withStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ApiError withTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ApiError withMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiError withDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
        return this;
    }

    public ApiError withSubErrors(List<? extends ApiSubError> subErrors) {
        this.subErrors = subErrors;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
