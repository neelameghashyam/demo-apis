package com.brodygaudel.demo.entity.settings;

import com.brodygaudel.demo.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "webhook")
public class Webhook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webhook_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Event event;

    @Column(name = "target_url")
    private String targetUrl;

    @Column(name = "created_by")
    private String createdBy;

    private String company;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "data_types")
    @ElementCollection
    private List<String> dataTypes;

    public enum Event {
        CHAT_STARTS, SUPPORT_CHAT_STARTS, CHAT_CONVERTED_TO_LEAD, CHAT_ACTIVATES_VERIFY_WEBSITE
    }
}