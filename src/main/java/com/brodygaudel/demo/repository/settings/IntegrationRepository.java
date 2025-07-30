package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.Integration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IntegrationRepository extends JpaRepository<Integration, Long> {

    @Query("SELECT i FROM Integration i WHERE LOWER(i.service) LIKE LOWER(:kw) OR LOWER(i.website) LIKE LOWER(:kw) OR LOWER(i.apiKey) LIKE LOWER(:kw)")
    Page<Integration> search(@Param("kw") String keyword, Pageable pageable);
}