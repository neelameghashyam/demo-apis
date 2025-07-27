package com.brodygaudel.demo.repository;

import com.brodygaudel.demo.entity.Website;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WebsiteRepository extends JpaRepository<Website, Long> {

    @Query("SELECT w FROM Website w WHERE w.domain LIKE :kw OR w.businessCategory LIKE :kw")
    Page<Website> search(@Param("kw") String keyword, Pageable pageable);
}