package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WebhookDataTypeDTO(
        Long id,
        @NotNull Long webhookId,
        @NotBlank String dataType
) {
}