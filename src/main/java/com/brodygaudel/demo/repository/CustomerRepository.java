package com.brodygaudel.demo.repository;

import com.brodygaudel.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.name LIKE :kw OR c.email LIKE :kw OR c.country LIKE :kw")
    Page<Customer> search(@Param("kw") String keyword, Pageable pageable);
}