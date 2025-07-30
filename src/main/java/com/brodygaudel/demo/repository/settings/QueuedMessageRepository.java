package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.QueuedMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QueuedMessageRepository extends JpaRepository<QueuedMessage, Long> {

    @Query("SELECT qm FROM QueuedMessage qm WHERE qm.message LIKE :kw OR qm.createdBy LIKE :kw OR qm.company LIKE :kw")
    Page<QueuedMessage> search(@Param("kw") String keyword, Pageable pageable);
}