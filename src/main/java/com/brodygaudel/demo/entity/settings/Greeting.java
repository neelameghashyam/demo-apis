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
@Table(name = "Greetings")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "greeting_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String greeting;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private Boolean visible;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum Type {
        All_Visitors, Returning_Visitors, First_Time_Visitors
    }
}