package com.brodygaudel.demo.dto.settings;

import java.time.LocalDateTime;
import java.util.List;

public record KnowledgeBaseDTO(
        Long id,
        Long userId,
        String questionTitle,
        String answerInformation,
        String keywords,
        List<String> websites,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}