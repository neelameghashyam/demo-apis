package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t WHERE t.tag LIKE :kw OR t.createdBy LIKE :kw OR t.company LIKE :kw")
    Page<Tag> search(@Param("kw") String keyword, Pageable pageable);
}