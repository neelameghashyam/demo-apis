package com.brodygaudel.demo.dto;

import java.time.LocalDateTime;

public record CustomerDTO(
        Long id,
        String name,
        String email,
        String country,
        LocalDateTime dateAdded,
        String integrations,
        String activePlanName,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}