package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record KnowledgeBaseWebsiteDTO(
        Long id,
        @NotNull Long knowledgeBaseId,
        @NotBlank String website
) {
}