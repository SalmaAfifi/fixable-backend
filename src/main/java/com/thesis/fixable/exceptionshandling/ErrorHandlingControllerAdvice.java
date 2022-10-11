package com.thesis.fixable.exceptionshandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException e) {
        List<ApiValidationError> errorList = new ArrayList<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            errorList.add(new ApiValidationError(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return buildResponseEntity(new ApiError()
                .withSubErrors(errorList)
                .withStatus(HttpStatus.BAD_REQUEST)
                .withMessage("Validation error"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ApiValidationError> errorList = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorList.add(new ApiValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return buildResponseEntity(new ApiError()
                .withSubErrors(errorList)
                .withStatus(HttpStatus.BAD_REQUEST)
                .withMessage("Validation error"));
    }


    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    ResponseEntity<Object> onBadCredentialsException(BadCredentialsException e) {
        return buildResponseEntity(new ApiError().
                withStatus(HttpStatus.UNAUTHORIZED)
                .withMessage("Email address is not registered!"));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}