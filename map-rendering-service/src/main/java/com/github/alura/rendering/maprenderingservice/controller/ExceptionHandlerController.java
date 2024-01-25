package com.github.alura.rendering.maprenderingservice.controller;

import com.github.alura.rendering.maprenderingservice.exception.NotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Not Found: " + e.getMessage());
    }

    @ExceptionHandler({
            TypeMismatchException.class,
            NumberFormatException.class,
            IllegalArgumentException.class,
            MissingRequestValueException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidInputException(Exception ex) {
        return ResponseEntity.badRequest().body("Invalid input: " + ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error: " + e.getMessage());
    }


}