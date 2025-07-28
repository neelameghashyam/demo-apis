package com.brodygaudel.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvoiceDTO(
        Long id,
        String companyName,
        String customerEmail,
        LocalDateTime createdAt,
        String invoiceNo,
        BigDecimal price,
        String currency,
        String status,
        LocalDateTime deletedAt
) {
}