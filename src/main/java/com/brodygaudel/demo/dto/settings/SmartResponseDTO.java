package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record SmartResponseDTO(
        Long id,
        Long userId,
        @NotBlank String response,
        @NotBlank @Size(max = 100) String createdBy,
        @NotBlank @Size(max = 100) String company,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt,
        List<String> shortcuts,
        List<String> websites
) {
}