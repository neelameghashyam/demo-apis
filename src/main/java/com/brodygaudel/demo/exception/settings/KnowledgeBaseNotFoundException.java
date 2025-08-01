package com.brodygaudel.demo.exception.settings;

public class KnowledgeBaseNotFoundException extends RuntimeException {
    public KnowledgeBaseNotFoundException(String message) {
        super(message);
    }
}