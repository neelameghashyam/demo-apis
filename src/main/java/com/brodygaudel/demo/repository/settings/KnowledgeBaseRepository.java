package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.KnowledgeBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Long> {

    @Query("SELECT kb FROM KnowledgeBase kb WHERE kb.questionTitle LIKE :kw OR kb.answerInformation LIKE :kw OR kb.keywords LIKE :kw")
    Page<KnowledgeBase> search(@Param("kw") String keyword, Pageable pageable);
}