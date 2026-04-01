package com.qode.springimpl.exception;

public class DuplicateBeanNameException extends RuntimeException {
    public DuplicateBeanNameException(String message) {
        super(message);
    }
}
