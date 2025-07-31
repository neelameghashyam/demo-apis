package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    @Query("SELECT t FROM Template t WHERE t.businessCategory LIKE :kw OR t.businessSubcategory LIKE :kw OR t.createdBy LIKE :kw")
    Page<Template> search(@Param("kw") String keyword, Pageable pageable);
}