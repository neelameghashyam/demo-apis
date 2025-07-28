package com.brodygaudel.demo.repository;

import com.brodygaudel.demo.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.companyName LIKE :kw OR i.customerEmail LIKE :kw OR i.invoiceNo LIKE :kw")
    Page<Invoice> search(@Param("kw") String keyword, Pageable pageable);
}