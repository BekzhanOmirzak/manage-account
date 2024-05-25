package com.contacts.manage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> handleIllegalArgument(RuntimeException e) {
        var errorResponse = new ErrorResponse(e.getMessage(), "Internal Type");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
