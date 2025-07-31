package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record KnowledgeBaseDTO(
        Long id,
        Long userId,
        @NotBlank String questionTitle,
        @NotBlank String answerInformation,
        @NotBlank String keywords,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt,
        List<String> websites
) {
}