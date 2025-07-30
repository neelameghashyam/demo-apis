package com.brodygaudel.demo.exception.settings;

public class QueuedMessageNotFoundException extends Exception {
    public QueuedMessageNotFoundException(String message) {
        super(message);
    }
}