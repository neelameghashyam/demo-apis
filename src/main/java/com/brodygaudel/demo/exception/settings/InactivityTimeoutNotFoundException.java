package com.brodygaudel.demo.exception.settings;

public class InactivityTimeoutNotFoundException extends Exception {
    public InactivityTimeoutNotFoundException(String message) {
        super(message);
    }
}