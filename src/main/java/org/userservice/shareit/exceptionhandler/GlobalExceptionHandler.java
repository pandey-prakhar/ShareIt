package org.userservice.shareit.exceptionhandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.userservice.shareit.exceptions.FileNotFoundException;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOError(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("I/O error occurred: " + ex.getMessage());
    }
}
