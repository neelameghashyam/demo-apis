package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.GlobalNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GlobalNotificationRepository extends JpaRepository<GlobalNotification, Long> {

    @Query("SELECT gn FROM GlobalNotification gn WHERE gn.notificationsEmail LIKE :kw")
    Page<GlobalNotification> search(@Param("kw") String keyword, Pageable pageable);
}