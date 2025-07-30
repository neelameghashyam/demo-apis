package com.brodygaudel.demo.entity.settings;

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
@Table(name = "AvatarTemplates")
public class AvatarTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long id;

    @Column(name = "element_name", nullable = false)
    private String elementName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum Gender {
        Male, Female, Other
    }
}