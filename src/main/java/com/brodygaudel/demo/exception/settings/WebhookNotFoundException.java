package com.brodygaudel.demo.exception.settings;

public class WebhookNotFoundException extends Exception {
    public WebhookNotFoundException(String message) {
        super(message);
    }
}