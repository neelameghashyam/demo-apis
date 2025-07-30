package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.GlobalWebhook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GlobalWebhookRepository extends JpaRepository<GlobalWebhook, Long> {
    @Query("SELECT g FROM GlobalWebhook g WHERE LOWER(g.event) LIKE LOWER(:keyword) OR LOWER(g.targetUrl) LIKE LOWER(:keyword) OR LOWER(g.email) LIKE LOWER(:keyword)")
    Page<GlobalWebhook> search(@Param("keyword") String keyword, Pageable pageable);
}