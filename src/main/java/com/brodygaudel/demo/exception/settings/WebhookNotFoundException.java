package com.brodygaudel.demo.exception.settings;

public class WebhookNotFoundException extends RuntimeException {
    public WebhookNotFoundException(String message) {
        super(message);
    }
}