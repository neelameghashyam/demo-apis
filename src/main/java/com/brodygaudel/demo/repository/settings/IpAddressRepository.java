package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.IpAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IpAddressRepository extends JpaRepository<IpAddress, Long> {

    @Query("SELECT ia FROM IpAddress ia WHERE ia.ipAddress LIKE :kw")
    Page<IpAddress> search(@Param("kw") String keyword, Pageable pageable);
}