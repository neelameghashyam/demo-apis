package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.Greeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {

    @Query("SELECT g FROM Greeting g WHERE g.title LIKE :kw")
    Page<Greeting> search(@Param("kw") String keyword, Pageable pageable);
}