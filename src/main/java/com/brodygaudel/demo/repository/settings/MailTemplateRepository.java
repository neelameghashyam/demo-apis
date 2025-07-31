package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.MailTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {

    @Query("SELECT mt FROM MailTemplate mt WHERE mt.name LIKE :kw OR mt.useCase LIKE :kw OR mt.createdBy LIKE :kw OR mt.modifiedBy LIKE :kw")
    Page<MailTemplate> search(@Param("kw") String keyword, Pageable pageable);
}