package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record IntegrationDTO(
        Long id,
        Long userId,
        @NotBlank String service,
        @NotBlank @Size(max = 255) String website,
        @NotBlank @Size(max = 255) String apiKey,
        @NotNull Boolean isConfigured,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}