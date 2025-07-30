package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.SmartResponseShortcut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SmartResponseShortcutRepository extends JpaRepository<SmartResponseShortcut, Long> {

    @Query("SELECT srs FROM SmartResponseShortcut srs WHERE srs.shortcut LIKE :kw")
    Page<SmartResponseShortcut> search(@Param("kw") String keyword, Pageable pageable);
}