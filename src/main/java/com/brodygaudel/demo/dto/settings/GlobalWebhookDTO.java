package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record GlobalWebhookDTO(
        Long id,
        Long userId,
        @NotBlank String event,
        @NotNull Boolean dataTypeEnabled,
        @NotBlank String destination,
        @Size(max = 255) String email,
        @Size(max = 255) String targetUrl,
        @NotBlank @Size(max = 100) String createdBy,
        @NotBlank @Size(max = 100) String company,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}