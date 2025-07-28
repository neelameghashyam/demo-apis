package com.brodygaudel.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "price_plans")
public class PricePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "plan_description", columnDefinition = "TEXT")
    private String planDescription;

    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'inactive'")
    private String status;

    @Column(name = "default_plan", nullable = false)
    private Boolean defaultPlan;

    @Column(name = "free_plan", nullable = false)
    private Boolean freePlan;

    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'public'")
    private String type;

    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @Column(name = "price_monthly", nullable = false)
    private BigDecimal priceMonthly;

    @Column(name = "price_annually", nullable = false)
    private BigDecimal priceAnnually;

    @Column(name = "unlimited_chat", nullable = false)
    private Boolean unlimitedChat;

    @Column(name = "number_of_chats", nullable = false)
    private Integer numberOfChats;

    @Column(name = "extra_chat_amount", nullable = false)
    private BigDecimal extraChatAmount;

    @Column(name = "unlimited_chat_history_storage", nullable = false)
    private Boolean unlimitedChatHistoryStorage;

    @Column(name = "chat_history_duration_days", nullable = false)
    private Integer chatHistoryDurationDays;

    @Column(name = "cost_per_extra_day_of_storage", nullable = false)
    private BigDecimal costPerExtraDayOfStorage;

    @Column(name = "unlimited_users", nullable = false)
    private Boolean unlimitedUsers;

    @Column(name = "number_of_users", nullable = false)
    private Integer numberOfUsers;

    @Column(name = "extra_user_cost", nullable = false)
    private BigDecimal extraUserCost;

    @Column(name = "number_of_websites", nullable = false)
    private Integer numberOfWebsites;

    @Column(name = "extra_website_cost", nullable = false)
    private BigDecimal extraWebsiteCost;

    @Column(name = "chat_takeover", nullable = false)
    private Boolean chatTakeover;

    @Column(name = "chat_tagging", nullable = false)
    private Boolean chatTagging;

    @Column(name = "chat_transcript", nullable = false)
    private Boolean chatTranscript;

    @Column(name = "chatbot_openai_included", nullable = false)
    private Boolean chatbotOpenaiIncluded;

    @Column(name = "managed_account", nullable = false)
    private Boolean managedAccount;

    @Column(name = "custom_plan", nullable = false)
    private Boolean customPlan;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}