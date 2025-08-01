package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.SmartResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SmartResponseRepository extends JpaRepository<SmartResponse, Long> {
    @Query("SELECT s FROM SmartResponse s LEFT JOIN s.shortcuts sc LEFT JOIN s.websites w WHERE s.response LIKE %:keyword% OR s.createdBy LIKE %:keyword% OR s.company LIKE %:keyword% OR sc LIKE %:keyword% OR w LIKE %:keyword%")
Page<SmartResponse> search(String keyword, Pageable pageable);
}