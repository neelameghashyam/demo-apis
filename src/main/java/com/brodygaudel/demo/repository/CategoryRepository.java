package com.brodygaudel.demo.repository;

import com.brodygaudel.demo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.name like :kw or c.description like :kw order by c.name desc ")
    Page<Category> search(@Param("kw") String keyword, Pageable pageable);

}
