package com.brodygaudel.demo.dto;

import java.time.LocalDateTime;

public record WebsiteDTO(
        Long id,
        String protocol,
        String domain,
        Long companyId,
        String businessCategory,
        LocalDateTime dateAdded,
        Boolean isActive,
        Boolean isVerified,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}