package com.brodygaudel.demo.dto.settings;

import java.time.LocalDateTime;
import java.util.List;

public record WebhookDTO(
        Long id,
        Long userId,
        String event,
        List<String> dataTypes,
        String targetUrl,
        String createdBy,
        String company,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}