package com.brodygaudel.demo.repository;

import com.brodygaudel.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email LIKE :kw OR u.firstName LIKE :kw OR u.lastName LIKE :kw")
    Page<User> search(@Param("kw") String keyword, Pageable pageable);
}