package com.sboldur.springangularairports.controller;

import com.sboldur.springangularairports.exceptions.EntityNotFoundException;
import com.sboldur.springangularairports.exceptions.ExceptionResponse;
import com.sboldur.springangularairports.exceptions.InvalidTopException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InfoControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(EntityNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse("Not Found", ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTopException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(InvalidTopException ex) {
        ExceptionResponse response = new ExceptionResponse("Validation error:", ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleException(Throwable ex) {
        ExceptionResponse response = new ExceptionResponse("Internal Error", ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
