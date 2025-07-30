package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record TagDTO(
        Long id,
        Long userId,
        @NotBlank @Size(max = 100) String tag,
        @NotNull Boolean isDefault,
        @NotBlank @Size(max = 100) String createdBy,
        @NotBlank @Size(max = 100) String company,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}