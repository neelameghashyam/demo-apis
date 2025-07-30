package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.Translation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

    @Query("SELECT t FROM Translation t WHERE t.language LIKE :kw OR t.greetingText LIKE :kw")
    Page<Translation> search(@Param("kw") String keyword, Pageable pageable);
}