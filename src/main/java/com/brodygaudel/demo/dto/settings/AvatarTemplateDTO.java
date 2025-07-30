package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AvatarTemplateDTO(
        Long id,
        @NotBlank String elementName,
        @NotBlank String gender,
        @NotBlank String imageUrl,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}