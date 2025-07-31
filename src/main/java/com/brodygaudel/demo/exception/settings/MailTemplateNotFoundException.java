package com.brodygaudel.demo.exception.settings;

public class MailTemplateNotFoundException extends Exception {
    public MailTemplateNotFoundException(String message) {
        super(message);
    }
}