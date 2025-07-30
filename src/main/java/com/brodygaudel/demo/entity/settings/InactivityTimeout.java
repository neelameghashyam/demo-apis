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
@Table(name = "inactivity_timeouts")
public class InactivityTimeout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeout_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "agent_not_responding_enabled", nullable = false)
    private Boolean agentNotRespondingEnabled;

    @Column(name = "agent_not_responding_minutes", nullable = false)
    private Integer agentNotRespondingMinutes;

    @Column(name = "agent_not_responding_seconds", nullable = false)
    private Integer agentNotRespondingSeconds;

    @Column(name = "archive_chat_enabled", nullable = false)
    private Boolean archiveChatEnabled;

    @Column(name = "archive_chat_minutes", nullable = false)
    private Integer archiveChatMinutes;

    @Column(name = "archive_chat_seconds", nullable = false)
    private Integer archiveChatSeconds;

    @Column(name = "visitor_inactivity_minutes", nullable = false)
    private Integer visitorInactivityMinutes;

    @Column(name = "visitor_inactivity_seconds", nullable = false)
    private Integer visitorInactivitySeconds;

    @Column(name = "timeout_message", columnDefinition = "TEXT")
    private String timeoutMessage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}