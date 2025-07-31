package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TemplateDTO(
        Long id,
        Long userId,
        @NotBlank String businessCategory,
        @NotBlank String businessSubcategory,
        @NotBlank String createdBy,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}