package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Query("SELECT a FROM Announcement a WHERE a.title LIKE :kw")
    Page<Announcement> search(@Param("kw") String keyword, Pageable pageable);
}