package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record WebhookDTO(
        Long id,
        Long userId,
        @NotBlank String event,
        @NotBlank String targetUrl,
        @NotBlank String createdBy,
        @NotBlank String company,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt,
        List<String> dataTypes
) {
}