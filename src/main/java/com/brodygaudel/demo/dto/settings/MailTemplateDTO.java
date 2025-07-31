package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MailTemplateDTO(
        Long id,
        Long userId,
        @NotBlank String name,
        @NotBlank String useCase,
        @NotBlank String subject,
        @NotNull Boolean active,
        @NotBlank String createdBy,
        @NotNull LocalDateTime createdAt,
        @NotBlank String modifiedBy,
        @NotNull LocalDateTime modifiedAt
) {
}