package com.college.digvijay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DivisionNotFound extends RuntimeException {
    public DivisionNotFound(String message) {
        super(message);
    }
}
