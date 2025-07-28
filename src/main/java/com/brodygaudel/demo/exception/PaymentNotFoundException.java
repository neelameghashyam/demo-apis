package com.brodygaudel.demo.exception;

public class PaymentNotFoundException extends Exception {
    public PaymentNotFoundException(String message) {
        super(message);
    }
}