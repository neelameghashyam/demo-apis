package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.SmartResponseWebsite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SmartResponseWebsiteRepository extends JpaRepository<SmartResponseWebsite, Long> {

    @Query("SELECT srw FROM SmartResponseWebsite srw WHERE srw.website LIKE :kw")
    Page<SmartResponseWebsite> search(@Param("kw") String keyword, Pageable pageable);
}