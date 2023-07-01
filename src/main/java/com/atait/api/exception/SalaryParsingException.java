package com.atait.api.exception;

public class SalaryParsingException extends RuntimeException{
    public SalaryParsingException(String message) {
        super("Impossible to parse the salary to integer " + message);
    }
}
