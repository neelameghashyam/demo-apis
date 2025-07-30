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
@Table(name = "Announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "page_type", nullable = false)
    private PageType pageType;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum PageType {
        Login, Dashboard
    }
}