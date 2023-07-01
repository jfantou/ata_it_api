package com.atait.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FilterNotFoundException extends RuntimeException{

    public FilterNotFoundException(String message) {
        super("We can't filter this column (filter not available): " + message);
    }

}
