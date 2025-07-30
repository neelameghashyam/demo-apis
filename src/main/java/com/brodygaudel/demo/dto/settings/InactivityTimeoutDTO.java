package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InactivityTimeoutDTO(
        Long id,
        Long userId,
        @NotNull Boolean agentNotRespondingEnabled,
        @NotNull @Min(0) @Max(59) Integer agentNotRespondingMinutes,
        @NotNull @Min(0) @Max(59) Integer agentNotRespondingSeconds,
        @NotNull Boolean archiveChatEnabled,
        @NotNull @Min(0) @Max(59) Integer archiveChatMinutes,
        @NotNull @Min(0) @Max(59) Integer archiveChatSeconds,
        @NotNull @Min(0) @Max(59) Integer visitorInactivityMinutes,
        @NotNull @Min(0) @Max(59) Integer visitorInactivitySeconds,
        String timeoutMessage,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}