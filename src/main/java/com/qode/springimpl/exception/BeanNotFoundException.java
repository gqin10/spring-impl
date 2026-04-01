package com.qode.springimpl.exception;

public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(String message) {
        super(message);
    }
}
