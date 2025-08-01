package com.brodygaudel.demo.entity.settings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "smart_responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmartResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smart_response_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "response", nullable = false, columnDefinition = "TEXT")
    private String response;

    @ElementCollection
    @CollectionTable(name = "smart_response_shortcuts", joinColumns = @JoinColumn(name = "smart_response_id"))
    @Column(name = "shortcut")
    private List<String> shortcuts;

    @ElementCollection
    @CollectionTable(name = "smart_response_websites", joinColumns = @JoinColumn(name = "smart_response_id"))
    @Column(name = "website")
    private List<String> websites;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}