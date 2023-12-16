package com.brodygaudel.demo.util;

import com.brodygaudel.demo.dto.CategoryDTO;
import com.brodygaudel.demo.dto.ProductDTO;
import com.brodygaudel.demo.entity.Category;
import com.brodygaudel.demo.entity.Product;

import java.util.List;

public interface Mappers {
    ProductDTO fromProduct(Product product);
    List<ProductDTO> fromListOfProducts(List<Product> products);
    Product fromProductDTO(ProductDTO productDTO);
    Category fromCategoryDTO(CategoryDTO categoryDTO);
    CategoryDTO fromCategory(Category category);
    List<CategoryDTO> fromListOfCategories(List<Category> categories);
}
