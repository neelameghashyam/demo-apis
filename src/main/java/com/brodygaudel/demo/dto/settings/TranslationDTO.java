package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TranslationDTO(
        Long id,
        @NotNull Long greetingId,
        @NotBlank String language,
        @NotBlank String greetingText,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}