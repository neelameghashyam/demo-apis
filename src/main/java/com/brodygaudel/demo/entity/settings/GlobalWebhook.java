package com.brodygaudel.demo.entity.settings;

import com.brodygaudel.demo.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "global_webhooks")
public class GlobalWebhook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "global_webhook_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private Event event;

    @Column(name = "data_type_enabled", nullable = false)
    private Boolean dataTypeEnabled;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Destination destination;

    @Column(length = 255)
    private String email;

    @Column(name = "target_url", length = 255)
    private String targetUrl;

    @Column(name = "created_by", nullable = false, length = 100)
    private String createdBy;

    @Column(nullable = false, length = 100)
    private String company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum Event {
        NEW_CUSTOMER, NEW_EMPLOYEE, RESTORE_PASSWORD, NEW_INVOICE, NEW_PAYMENT
    }

    public enum Destination {
        TARGET_URL, EMAIL, BOTH
    }
}