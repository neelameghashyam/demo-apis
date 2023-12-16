package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.CategoryDTO;
import com.brodygaudel.demo.entity.Category;
import com.brodygaudel.demo.exception.CategoryNotFoundException;
import com.brodygaudel.demo.repository.CategoryRepository;
import com.brodygaudel.demo.util.Mappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private Mappers mappers;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository, mappers);
    }

    @Test
    void saveCategory() {
        CategoryDTO categoryDTO = new CategoryDTO(1L, "name", "description");
        Category category = new Category(1L, "name", "description", null);
        when(mappers.fromCategoryDTO(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(mappers.fromCategory(category)).thenReturn(categoryDTO);

        CategoryDTO response = categoryService.saveCategory(categoryDTO);
        assertNotNull(response);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void updateCategory() throws CategoryNotFoundException {
        CategoryDTO categoryDTO = new CategoryDTO(1L, "new_name", "new_description");
        Category category = new Category(1L, "name", "description", null);
        Category categoryUpdated = new Category(1L, "new_name", "new_description", null);

        when(categoryRepository.findById(categoryDTO.id())).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(categoryUpdated);
        when(mappers.fromCategory(categoryUpdated)).thenReturn(categoryDTO);

        CategoryDTO response = categoryService.updateCategory(categoryDTO);
        assertNotNull(response);
        assertEquals(response.id(), categoryDTO.id());
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void findCategoryById() throws CategoryNotFoundException {
        CategoryDTO categoryDTO = new CategoryDTO(1L, "name", "description");
        Category category = new Category(1L, "name", "description", null);

        when(categoryRepository.findById(categoryDTO.id())).thenReturn(Optional.of(category));
        when(mappers.fromCategory(category)).thenReturn(categoryDTO);

        CategoryDTO response = categoryService.findCategoryById(1L);
        assertNotNull(response);
        assertEquals(1L, response.id());
    }

    @Test
    void findAllCategories() {
        Category c1 = new Category(1L, "name 1", "description 1", null);
        Category c2 = new Category(2L, "name 2", "description 2", null);
        List<Category> categories = List.of(c1, c2);

        CategoryDTO dto1 = new CategoryDTO(1L, "name 1", "description 1");
        CategoryDTO dt02 = new CategoryDTO(2L, "name 2", "description 2");
        List<CategoryDTO> categoryDTOS = List.of(dto1, dt02);

        when(categoryRepository.findAll()).thenReturn(categories);
        when(mappers.fromListOfCategories(categories)).thenReturn(categoryDTOS);

        List<CategoryDTO> response = categoryService.findAllCategories();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void searchCategories() {
        String keyword = "name";
        int page = 0;
        int size = 2;
        Category c1 = new Category(1L, "name 1", "description 1", null);
        Category c2 = new Category(2L, "name 2", "description 2", null);
        List<Category> categories = List.of(c1, c2);

        CategoryDTO dto1 = new CategoryDTO(1L, "name 1", "description 1");
        CategoryDTO dt02 = new CategoryDTO(2L, "name 2", "description 2");
        List<CategoryDTO> categoryDTOS = List.of(dto1, dt02);

        when(categoryRepository.search("%"+keyword+"%", PageRequest.of(page, size))).thenReturn(
                new PageImpl<>(categories, PageRequest.of(page, size), 2)
        );
        when(mappers.fromListOfCategories(anyList())).thenReturn(categoryDTOS);

        List<CategoryDTO> response = categoryService.searchCategories(keyword, page, size);
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void deleteCategoryById() {
        Category category = new Category(1L, "name", "description", null);
        categoryService.deleteCategoryById(category.getId());
        verify(categoryRepository, times(1)).deleteById(category.getId());
    }

    @Test
    void deleteAllCategories() {
        categoryService.deleteAllCategories();
        verify(categoryRepository, times(1)).deleteAll();
    }
}