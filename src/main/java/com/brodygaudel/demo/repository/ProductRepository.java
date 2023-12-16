package com.brodygaudel.demo.repository;

import com.brodygaudel.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("select p from Product p where p.name like :kw or p.description like :kw or p.barcode like :kw order by p.name desc")
    Page<Product> search(@Param("kw") String keyword, Pageable pageable);

    @Query("select p from Product p where p.category.id =?1")
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}
