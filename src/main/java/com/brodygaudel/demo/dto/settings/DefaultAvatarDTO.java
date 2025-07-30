package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DefaultAvatarDTO(
        Long id,
        @NotNull Long userId,
        @NotBlank String name,
        @NotBlank String jobTitle,
        String avatarImageUrl,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}