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
@Table(name = "global_notifications")
public class GlobalNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "use_same_email", nullable = false)
    private Boolean useSameEmail;

    @Column(name = "notifications_email")
    private String notificationsEmail;

    @Column(name = "notify_lead", nullable = false)
    private Boolean notifyLead;

    @Column(name = "notify_service_chat", nullable = false)
    private Boolean notifyServiceChat;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}