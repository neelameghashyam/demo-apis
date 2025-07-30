package com.brodygaudel.demo.repository.settings;

import com.brodygaudel.demo.entity.settings.DefaultAvatar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DefaultAvatarRepository extends JpaRepository<DefaultAvatar, Long> {

    @Query("SELECT da FROM DefaultAvatar da WHERE da.name LIKE :kw OR da.jobTitle LIKE :kw")
    Page<DefaultAvatar> search(@Param("kw") String keyword, Pageable pageable);
}