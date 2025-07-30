package com.brodygaudel.demo.entity.settings;

import com.brodygaudel.demo.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "smart_responses")
public class SmartResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smart_response_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String response;

    @Column(name = "created_by", nullable = false, length = 100)
    private String createdBy;

    @Column(nullable = false, length = 100)
    private String company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "smartResponse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SmartResponseShortcut> shortcuts;

    @OneToMany(mappedBy = "smartResponse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SmartResponseWebsite> websites;
}