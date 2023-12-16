package com.brodygaudel.demo.controller;

import com.brodygaudel.demo.dto.CategoryDTO;
import com.brodygaudel.demo.exception.CategoryNotFoundException;
import com.brodygaudel.demo.service.CategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.saveCategory(categoryDTO);
    }

    @PutMapping("/update")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) throws CategoryNotFoundException{
        return categoryService.updateCategory(categoryDTO);
    }

    @GetMapping("/get/{id}")
    public CategoryDTO findCategoryById(@PathVariable Long id) throws CategoryNotFoundException{
        return categoryService.findCategoryById(id);
    }

    @GetMapping("/list")
    public List<CategoryDTO> findAllCategories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("/search")
    public List<CategoryDTO> searchCategories(@RequestParam( defaultValue = "") String keyword,
                                              @RequestParam( defaultValue = "0") int page,
                                              @RequestParam( defaultValue = "10") int size){
        return categoryService.searchCategories(keyword, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllCategories(){
        categoryService.deleteAllCategories();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
