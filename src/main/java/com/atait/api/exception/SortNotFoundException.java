package com.atait.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SortNotFoundException extends RuntimeException{
    public SortNotFoundException(String message) {
        super("This column " + message + " don't exist");
    }
}
