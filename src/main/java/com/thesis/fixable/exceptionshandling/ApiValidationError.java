package com.thesis.fixable.exceptionshandling;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final class ApiValidationError extends ApiSubError {

    private final String fieldName;

    private final String message;


    @JsonCreator
    public ApiValidationError(
            @JsonProperty("fieldName") String fieldName,
            @JsonProperty("message") String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}

