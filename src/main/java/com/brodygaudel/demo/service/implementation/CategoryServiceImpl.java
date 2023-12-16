package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.CategoryDTO;
import com.brodygaudel.demo.entity.Category;
import com.brodygaudel.demo.exception.CategoryNotFoundException;
import com.brodygaudel.demo.repository.CategoryRepository;
import com.brodygaudel.demo.service.CategoryService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Mappers mappers;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Mappers mappers) {
        this.categoryRepository = categoryRepository;
        this.mappers = mappers;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        log.info("In saveCategory()");
        Category category = mappers.fromCategoryDTO(categoryDTO);
        Category categorySaved = categoryRepository.save(category);
        log.info("category saved with id : "+categorySaved.getId());
        return mappers.fromCategory(categorySaved);
    }

    @Override
    public CategoryDTO updateCategory(@NotNull CategoryDTO categoryDTO) throws CategoryNotFoundException {
        log.info("In updateCategory()");
        Category category = categoryRepository.findById(categoryDTO.id()).orElseThrow( () -> new CategoryNotFoundException("category not found"));
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        Category categoryUpdated = categoryRepository.save(category);
        log.info("category updated");
        return mappers.fromCategory(categoryUpdated);
    }

    @Override
    public CategoryDTO findCategoryById(Long id) throws CategoryNotFoundException {
        log.info("In findCategoryById()");
        Category category = categoryRepository.findById(id).orElseThrow( () -> new CategoryNotFoundException("category not found"));
        log.info(" category found with id :"+category.getId());
        return mappers.fromCategory(category);
    }

    @Override
    public List<CategoryDTO> findAllCategories() {
        log.info("In findAllCategories()");
        List<Category> categories = categoryRepository.findAll();
        log.info("all categories found");
        return mappers.fromListOfCategories(categories);
    }

    @Override
    public List<CategoryDTO> searchCategories(String keyword, int page, int size) {
        log.info("In searchCategories()");
        Page<Category> categoryPage = categoryRepository.search("%"+keyword+"%", PageRequest.of(page, size));
        log.info(categoryPage.getContent().size()+" categories found");
        return mappers.fromListOfCategories(categoryPage.getContent());
    }

    @Override
    public void deleteCategoryById(Long id) {
        log.info("In deleteCategoryById()");
        categoryRepository.deleteById(id);
        log.info("category deleted");
    }

    @Override
    public void deleteAllCategories() {
        log.info("In deleteAllCategories()");
        categoryRepository.deleteAll();
        log.info("all categories deleted");
    }
}
