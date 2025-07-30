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
@Table(name = "EyeCatchers")
public class EyeCatcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eye_catcher_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(name = "background_color", nullable = false)
    private String backgroundColor;

    @Column(name = "text_color", nullable = false)
    private String textColor;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private String company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}