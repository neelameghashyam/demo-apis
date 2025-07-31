package com.brodygaudel.demo.entity.settings;

import com.brodygaudel.demo.entity.User;
import com.brodygaudel.demo.entity.settings.GlobalWebhook.Event;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "webhook") // Specify the correct table name
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Webhook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webhook_id") // Map to the correct column name
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "event", nullable = false)
    private Event event;

    @Column(name = "target_url", nullable = false)
    private String targetUrl;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "webhook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WebhookDataType> dataTypes;

    public enum DataType {
    VISITOR_INFO, CHAT_INFO, LOCATION_INFO, TECHNOLOGY_INFO, SECURITY_INFO, CUSTOM_FIELDS
}
}