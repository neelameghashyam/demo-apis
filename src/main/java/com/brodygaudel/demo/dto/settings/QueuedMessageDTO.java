package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record QueuedMessageDTO(
        Long id,
        Long userId,
        @NotBlank String message,
        @NotBlank @Size(max = 7) String backgroundColor,
        @NotBlank @Size(max = 7) String textColor,
        @Size(max = 255) String imageUrl,
        @NotBlank @Size(max = 100) String createdBy,
        @NotBlank @Size(max = 100) String company,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}