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
@Table(name = "integrations", uniqueConstraints = @UniqueConstraint(columnNames = {"service", "website"}))
public class Integration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "integration_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Service service;

    @Column(nullable = false, length = 255)
    private String website;

    @Column(name = "api_key", nullable = false, length = 255)
    private String apiKey;

    @Column(name = "is_configured", nullable = false)
    private Boolean isConfigured;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum Service {
        ZAPIER, DRIFT
    }
}