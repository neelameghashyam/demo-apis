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
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(columnNames = {"tag"}))
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 100)
    private String tag;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @Column(name = "created_by", nullable = false, length = 100)
    private String createdBy;

    @Column(nullable = false, length = 100)
    private String company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}