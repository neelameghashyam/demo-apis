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
@Table(name = "Translations")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translation_id")
    private Long id;

   @ManyToOne
@JoinColumn(name = "greeting_id", nullable = false)
private Greeting greeting;

    @Column(nullable = false)
    private String language;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String greetingText;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}