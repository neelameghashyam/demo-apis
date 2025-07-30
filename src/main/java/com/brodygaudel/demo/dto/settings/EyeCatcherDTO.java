package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EyeCatcherDTO(
        Long id,
        Long userId,
        @NotBlank String title,
        @NotBlank String text,
        @NotBlank String backgroundColor,
        @NotBlank String textColor,
        String imageUrl,
        @NotBlank String createdBy,
        @NotBlank String company,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}