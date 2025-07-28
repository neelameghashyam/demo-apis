package com.brodygaudel.demo.repository;

import com.brodygaudel.demo.entity.PricePlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PricePlanRepository extends JpaRepository<PricePlan, Long> {

    @Query("SELECT p FROM PricePlan p WHERE p.planName LIKE :kw OR p.planDescription LIKE :kw")
    Page<PricePlan> search(@Param("kw") String keyword, Pageable pageable);
}