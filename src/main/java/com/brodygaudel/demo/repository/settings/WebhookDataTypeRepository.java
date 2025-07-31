package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.WebhookDataType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WebhookDataTypeRepository extends JpaRepository<WebhookDataType, Long> {

    @Query("SELECT wdt FROM WebhookDataType wdt WHERE CAST(wdt.dataType AS string) LIKE :keyword OR wdt.webhook.targetUrl LIKE :keyword OR wdt.webhook.createdBy LIKE :keyword OR wdt.webhook.company LIKE :keyword")
    Page<WebhookDataType> search(String keyword, Pageable pageable);

    void deleteByWebhookId(Long webhookId);
}