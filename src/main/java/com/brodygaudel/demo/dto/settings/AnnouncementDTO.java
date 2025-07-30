package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AnnouncementDTO(
        Long id,
        Long userId,
        @NotBlank String pageType,
        @NotBlank String title,
        @NotBlank String text,
        String imageUrl,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}