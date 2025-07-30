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
@Table(name = "queued_messages")
public class QueuedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queued_message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(name = "background_color", nullable = false, length = 7)
    private String backgroundColor;

    @Column(name = "text_color", nullable = false, length = 7)
    private String textColor;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "created_by", nullable = false, length = 100)
    private String createdBy;

    @Column(nullable = false, length = 100)
    private String company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}