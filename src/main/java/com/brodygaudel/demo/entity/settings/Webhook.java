package com.brodygaudel.demo.entity.settings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "webhooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Webhook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webhook_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event", nullable = false)
    private Event event;

    @ElementCollection
    @CollectionTable(name = "webhook_data_types", joinColumns = @JoinColumn(name = "webhook_id"))
    @Column(name = "data_type")
    private List<String> dataTypes;

    @Column(name = "target_url", nullable = false)
    private String targetUrl;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum Event {
        CHAT_STARTS("Chat starts"),
        SUPPORT_CHAT_STARTS("Support chat starts"),
        CHAT_CONVERTED_TO_LEAD("Chat converted to lead"),
        CHAT_ACTIVATES_VERIFY_WEBSITE("Chat activates/verify website");

        private final String value;

        Event(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}