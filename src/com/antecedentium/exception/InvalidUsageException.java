package com.antecedentium.exception;

public class InvalidUsageException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid usage";
    }
}