package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.Webhook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WebhookRepository extends JpaRepository<Webhook, Long> {

    @Query("SELECT w FROM Webhook w WHERE LOWER(w.event) LIKE LOWER(:keyword) OR LOWER(w.targetUrl) LIKE LOWER(:keyword) OR LOWER(w.createdBy) LIKE LOWER(:keyword) OR LOWER(w.company) LIKE LOWER(:keyword)")
    Page<Webhook> search(@Param("keyword") String keyword, Pageable pageable);
}