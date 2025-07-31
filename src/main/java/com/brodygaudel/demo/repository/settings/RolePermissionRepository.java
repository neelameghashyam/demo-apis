package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    @Query("SELECT rp FROM RolePermission rp WHERE rp.userRole LIKE :kw")
    Page<RolePermission> search(@Param("kw") String keyword, Pageable pageable);
}