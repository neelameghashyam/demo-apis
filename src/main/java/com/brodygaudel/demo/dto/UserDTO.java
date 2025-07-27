package com.brodygaudel.demo.dto;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String email,
        String role,
        String passwordHash,
        String firstName,
        String lastName,
        String jobTitle,
        String department,
        Long companyId,
        Integer simultaneousChatLimit,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}