package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.SmartResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SmartResponseRepository extends JpaRepository<SmartResponse, Long> {

    @Query("SELECT sr FROM SmartResponse sr WHERE sr.response LIKE :kw OR sr.createdBy LIKE :kw OR sr.company LIKE :kw")
    Page<SmartResponse> search(@Param("kw") String keyword, Pageable pageable);
}