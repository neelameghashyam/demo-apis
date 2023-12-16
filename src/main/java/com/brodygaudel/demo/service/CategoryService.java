package com.brodygaudel.demo.service;

import com.brodygaudel.demo.dto.CategoryDTO;
import com.brodygaudel.demo.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO) throws CategoryNotFoundException;
    CategoryDTO findCategoryById(Long id) throws CategoryNotFoundException;
    List<CategoryDTO> findAllCategories();
    List<CategoryDTO> searchCategories(String keyword, int page, int size);
    void deleteCategoryById(Long id);
    void deleteAllCategories();
}
