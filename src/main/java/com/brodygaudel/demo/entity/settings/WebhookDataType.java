package com.brodygaudel.demo.entity.settings;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WebhookDataType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "webhook_id", nullable = false)
    private Webhook webhook;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DataType dataType;

   public enum DataType {
        VISITOR_INFO, CHAT_INFO, LOCATION_INFO, TECHNOLOGY_INFO, SECURITY_INFO, CUSTOM_FIELDS,MESSAGE, USER_INFO, CHAT_DETAILS, SUPPORT_CHAT_STARTS
    }
}