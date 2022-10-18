package com.thesis.fixable.exceptionshandling;

import com.thesis.fixable.exceptionshandling.exceptions.EmailAlreadyExistException;
import com.thesis.fixable.exceptionshandling.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

//TODO add exception handling for security exception
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
    ResponseEntity<Object> onAuthenticationException(AuthenticationException e) {
        return buildResponseEntity(new ApiError().
                withStatus(HttpStatus.UNAUTHORIZED)
                .withMessage(e.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<Object> onMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return buildResponseEntity(new ApiError().
                withStatus(HttpStatus.METHOD_NOT_ALLOWED)
                .withMessage(e.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    ResponseEntity<Object> onEmailAlreadyExistException(EmailAlreadyExistException e) {
        return buildResponseEntity(new ApiError().
                withStatus(e.getStatus())
                .withMessage(e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Object> onEntityNotFoundException(EntityNotFoundException e) {
        return buildResponseEntity(new ApiError().
                withStatus(e.getStatus())
                .withMessage(e.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}