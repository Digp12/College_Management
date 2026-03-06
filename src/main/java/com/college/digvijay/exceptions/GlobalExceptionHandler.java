package com.college.digvijay.exceptions;

import com.college.exceptions.StudentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClassesNotFound.class)
    public ResponseEntity<String> handleClassesNotFound(ClassesNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DivisionNotFound.class)
    public ResponseEntity<String> handleDivisionNotFound(DivisionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFound.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailExists.class)
    public ResponseEntity<String> handleEmailExists(EmailExists e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
