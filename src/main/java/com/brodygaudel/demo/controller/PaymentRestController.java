package com.brodygaudel.demo.controller;

import com.brodygaudel.demo.dto.PaymentDTO;
import com.brodygaudel.demo.exception.PaymentNotFoundException;
import com.brodygaudel.demo.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/save")
    public PaymentDTO savePayment(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.savePayment(paymentDTO);
    }

    @PutMapping("/update")
    public PaymentDTO updatePayment(@RequestBody PaymentDTO paymentDTO) throws PaymentNotFoundException {
        return paymentService.updatePayment(paymentDTO);
    }

    @GetMapping("/get/{id}")
    public PaymentDTO findPaymentById(@PathVariable Long id) throws PaymentNotFoundException {
        return paymentService.findPaymentById(id);
    }

    @GetMapping("/list")
    public List<PaymentDTO> findAllPayments() {
        return paymentService.findAllPayments();
    }

    @GetMapping("/search")
    public List<PaymentDTO> searchPayments(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return paymentService.searchPayments(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePaymentById(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllPayments() {
        paymentService.deleteAllPayments();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}