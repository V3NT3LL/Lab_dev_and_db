package com.example.Lab4;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IOException.class, SQLException.class, ClassNotFoundException.class, ParseException.class, InterruptedException.class})
    public ResponseEntity<String> handleCheckedException(Exception e) {
        String errorMessage = "";
        if (e instanceof IOException) {
            errorMessage = "IO Exception occurred";
        } else if (e instanceof SQLException) {
            errorMessage = "SQL Exception occurred";
        } else if (e instanceof ClassNotFoundException) {
            errorMessage = "Class Not Found Exception occurred";
        } else if (e instanceof ParseException) {
            errorMessage = "Parse Exception occurred";
        } else if (e instanceof InterruptedException) {
            errorMessage = "Interrupted Exception occurred";
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<String> handleUncheckedException(RuntimeException e) {
        String errorMessage = "Null Pointer Exception occurred";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

