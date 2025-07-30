package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.InactivityTimeout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InactivityTimeoutRepository extends JpaRepository<InactivityTimeout, Long> {

    @Query("SELECT it FROM InactivityTimeout it WHERE it.timeoutMessage LIKE :kw")
    Page<InactivityTimeout> search(@Param("kw") String keyword, Pageable pageable);
}