package com.brodygaudel.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PricePlanDTO(
        Long id,
        String planName,
        String planDescription,
        String status,
        Boolean defaultPlan,
        Boolean freePlan,
        String type,
        LocalDateTime dateAdded,
        BigDecimal priceMonthly,
        BigDecimal priceAnnually,
        Boolean unlimitedChat,
        Integer numberOfChats,
        BigDecimal extraChatAmount,
        Boolean unlimitedChatHistoryStorage,
        Integer chatHistoryDurationDays,
        BigDecimal costPerExtraDayOfStorage,
        Boolean unlimitedUsers,
        Integer numberOfUsers,
        BigDecimal extraUserCost,
        Integer numberOfWebsites,
        BigDecimal extraWebsiteCost,
        Boolean chatTakeover,
        Boolean chatTagging,
        Boolean chatTranscript,
        Boolean chatbotOpenaiIncluded,
        Boolean managedAccount,
        Boolean customPlan,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}