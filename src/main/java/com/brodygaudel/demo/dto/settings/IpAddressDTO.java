package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record IpAddressDTO(
        Long id,
        Long userId,
        @NotBlank String ipAddress,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}