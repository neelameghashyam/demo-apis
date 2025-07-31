package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.Webhook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WebhookRepository extends JpaRepository<Webhook, Long> {

    @Query("SELECT w FROM Webhook w WHERE w.targetUrl LIKE :keyword OR w.createdBy LIKE :keyword OR w.company LIKE :keyword OR CAST(w.event AS string) LIKE :keyword")
    Page<Webhook> search(String keyword, Pageable pageable);
}
