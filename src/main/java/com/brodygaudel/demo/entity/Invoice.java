package com.brodygaudel.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "invoice_no", nullable = false, unique = true)
    private String invoiceNo;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'USD'")
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('Paid', 'Pending', 'Overdue') DEFAULT 'Pending'")
    private Status status;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public enum Status {
        Paid, Pending, Overdue
    }
}