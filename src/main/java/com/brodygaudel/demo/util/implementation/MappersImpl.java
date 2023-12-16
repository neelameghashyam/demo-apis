package com.brodygaudel.demo.util.implementation;

import com.brodygaudel.demo.dto.CategoryDTO;
import com.brodygaudel.demo.dto.ProductDTO;
import com.brodygaudel.demo.entity.Category;
import com.brodygaudel.demo.entity.Product;
import com.brodygaudel.demo.util.Mappers;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public ProductDTO fromProduct(@NotNull Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBarcode(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId()
        );
    }

    @Override
    public List<ProductDTO> fromListOfProducts(@NotNull List<Product> products) {
        return products.stream().map(this::fromProduct).toList();
    }

    @Override
    public Product fromProductDTO(@NotNull ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.id())
                .barcode(productDTO.barcode())
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .quantity(productDTO.quantity())
                .build();
    }

    @Override
    public Category fromCategoryDTO(@NotNull CategoryDTO categoryDTO) {
        return Category.builder().id(categoryDTO.id()).name(categoryDTO.name()).description(categoryDTO.description()).build();
    }

    @Override
    public CategoryDTO fromCategory(@NotNull Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    @Override
    public List<CategoryDTO> fromListOfCategories(@NotNull List<Category> categories) {
        return categories.stream().map(this::fromCategory).toList();
    }
}
