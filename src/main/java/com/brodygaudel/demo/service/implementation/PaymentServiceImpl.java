package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.PaymentDTO;
import com.brodygaudel.demo.entity.Payment;
import com.brodygaudel.demo.exception.PaymentNotFoundException;
import com.brodygaudel.demo.repository.PaymentRepository;
import com.brodygaudel.demo.service.PaymentService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final Mappers mappers;

    public PaymentServiceImpl(PaymentRepository paymentRepository, Mappers mappers) {
        this.paymentRepository = paymentRepository;
        this.mappers = mappers;
    }

    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        log.info("In savePayment()");
        Payment payment = mappers.fromPaymentDTO(paymentDTO);
        Payment paymentSaved = paymentRepository.save(payment);
        log.info("Payment saved with id: {}", paymentSaved.getId());
        return mappers.fromPayment(paymentSaved);
    }

    @Override
    public PaymentDTO updatePayment(PaymentDTO paymentDTO) throws PaymentNotFoundException {
        log.info("In updatePayment()");
        Payment payment = paymentRepository.findById(paymentDTO.id())
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        payment.setAmount(paymentDTO.amount());
        payment.setCurrency(paymentDTO.currency());
        payment.setStatus(Payment.Status.valueOf(paymentDTO.status()));
        payment.setCancellationTime(paymentDTO.cancellationTime());
        payment.setCancellationReason(paymentDTO.cancellationReason());
        payment.setCreatedAt(paymentDTO.createdAt());
        payment.setUpdatedAt(paymentDTO.updatedAt());
        Payment paymentUpdated = paymentRepository.save(payment);
        log.info("Payment updated");
        return mappers.fromPayment(paymentUpdated);
    }

    @Override
    public PaymentDTO findPaymentById(Long id) throws PaymentNotFoundException {
        log.info("In findPaymentById()");
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        log.info("Payment found with id: {}", payment.getId());
        return mappers.fromPayment(payment);
    }

    @Override
    public List<PaymentDTO> findAllPayments() {
        log.info("In findAllPayments()");
        List<Payment> payments = paymentRepository.findAll();
        log.info("All payments found");
        return mappers.fromListOfPayments(payments);
    }

    @Override
    public List<PaymentDTO> searchPayments(String keyword, int page, int size) {
        log.info("In searchPayments()");
        Page<Payment> paymentPage = paymentRepository.search("%" + keyword + "%", PageRequest.of(page, size));
        log.info("{} payments found", paymentPage.getContent().size());
        return mappers.fromListOfPayments(paymentPage.getContent());
    }

    @Override
    public void deletePaymentById(Long id) {
        log.info("In deletePaymentById()");
        paymentRepository.deleteById(id);
        log.info("Payment deleted");
    }

    @Override
    public void deleteAllPayments() {
        log.info("In deleteAllPayments()");
        paymentRepository.deleteAll();
        log.info("All payments deleted");
    }
}