package com.brodygaudel.demo.exception;

public class WebsiteNotFoundException extends Exception {
    public WebsiteNotFoundException(String message) {
        super(message);
    }
}