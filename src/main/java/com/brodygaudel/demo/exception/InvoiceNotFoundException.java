package com.brodygaudel.demo.exception;

public class InvoiceNotFoundException extends Exception {
    public InvoiceNotFoundException(String message) {
        super(message);
    }
}