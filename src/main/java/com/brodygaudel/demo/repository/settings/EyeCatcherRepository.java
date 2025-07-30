package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.EyeCatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EyeCatcherRepository extends JpaRepository<EyeCatcher, Long> {

    @Query("SELECT e FROM EyeCatcher e WHERE e.title LIKE :kw OR e.createdBy LIKE :kw OR e.company LIKE :kw")
    Page<EyeCatcher> search(@Param("kw") String keyword, Pageable pageable);
}