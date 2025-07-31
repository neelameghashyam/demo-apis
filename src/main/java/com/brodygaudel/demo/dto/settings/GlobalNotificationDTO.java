package com.brodygaudel.demo.dto.settings;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record GlobalNotificationDTO(
        Long id,
        Long userId,
        @NotNull Boolean useSameEmail,
        String notificationsEmail,
        @NotNull Boolean notifyLead,
        @NotNull Boolean notifyServiceChat,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
}