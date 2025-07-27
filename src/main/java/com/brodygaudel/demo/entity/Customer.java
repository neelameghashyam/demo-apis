package com.brodygaudel.demo.entity;

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
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String country;

    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @Column(columnDefinition = "JSON")
    private String integrations;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_plan_name", nullable = false)
    private ActivePlanName activePlanName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('active', 'inactive') DEFAULT 'active'")
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum ActivePlanName {
        Free, Premium
    }

    public enum Status {
        active, inactive
    }
}