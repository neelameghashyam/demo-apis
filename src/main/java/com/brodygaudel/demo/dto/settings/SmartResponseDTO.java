package com.brodygaudel.demo.dto.settings;

import java.time.LocalDateTime;
import java.util.List;

public record SmartResponseDTO(
        Long id,
        Long userId,
        String response,
        List<String> shortcuts,
        List<String> websites,
        String createdBy,
        String company,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}