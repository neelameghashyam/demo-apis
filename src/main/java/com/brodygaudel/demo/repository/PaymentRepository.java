package com.brodygaudel.demo.repository;

import com.brodygaudel.demo.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.currency LIKE :kw OR p.cancellationReason LIKE :kw")
    Page<Payment> search(@Param("kw") String keyword, Pageable pageable);
}