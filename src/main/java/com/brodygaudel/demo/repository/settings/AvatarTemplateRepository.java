package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.AvatarTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvatarTemplateRepository extends JpaRepository<AvatarTemplate, Long> {

    @Query("SELECT at FROM AvatarTemplate at WHERE at.elementName LIKE :kw")
    Page<AvatarTemplate> search(@Param("kw") String keyword, Pageable pageable);
}