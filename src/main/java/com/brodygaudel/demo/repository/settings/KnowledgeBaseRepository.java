package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.KnowledgeBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Long> {
 @Query("SELECT k FROM KnowledgeBase k LEFT JOIN k.websites w WHERE k.questionTitle LIKE %:keyword% OR k.answerInformation LIKE %:keyword% OR k.keywords LIKE %:keyword% OR w LIKE %:keyword%")
Page<KnowledgeBase> search(String keyword, Pageable pageable);
}