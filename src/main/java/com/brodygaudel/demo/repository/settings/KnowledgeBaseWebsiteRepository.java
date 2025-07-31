package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.KnowledgeBaseWebsite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeBaseWebsiteRepository extends JpaRepository<KnowledgeBaseWebsite, Long> {

    @Query("SELECT kbw FROM KnowledgeBaseWebsite kbw WHERE kbw.website LIKE :keyword")
    Page<KnowledgeBaseWebsite> search(String keyword, Pageable pageable);

    void deleteByKnowledgeBaseId(Long knowledgeBaseId);
}