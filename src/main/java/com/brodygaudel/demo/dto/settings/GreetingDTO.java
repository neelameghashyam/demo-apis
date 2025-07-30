package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record GreetingDTO(
        Long id,
        Long userId,
        @NotBlank String title,
        @NotBlank String greeting,
        @NotBlank String type,
        @NotNull Boolean visible,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}