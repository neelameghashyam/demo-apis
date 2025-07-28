package com.brodygaudel.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        BigDecimal amount,
        String currency,
        String status,
        LocalDateTime cancellationTime,
        String cancellationReason
) {
}