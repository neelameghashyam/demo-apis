package com.brodygaudel.demo.service;

import com.brodygaudel.demo.dto.PaymentDTO;
import com.brodygaudel.demo.exception.PaymentNotFoundException;

import java.util.List;

public interface PaymentService {
    PaymentDTO savePayment(PaymentDTO paymentDTO);
    PaymentDTO updatePayment(PaymentDTO paymentDTO) throws PaymentNotFoundException;
    PaymentDTO findPaymentById(Long id) throws PaymentNotFoundException;
    List<PaymentDTO> findAllPayments();
    List<PaymentDTO> searchPayments(String keyword, int page, int size);
    void deletePaymentById(Long id);
    void deleteAllPayments();
}