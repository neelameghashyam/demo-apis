package com.brodygaudel.demo.service;

import com.brodygaudel.demo.dto.ProductDTO;
import com.brodygaudel.demo.dto.ProductsPageDTO;
import com.brodygaudel.demo.exception.CategoryNotFoundException;
import com.brodygaudel.demo.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO) throws CategoryNotFoundException;
    ProductDTO updateProduct(ProductDTO productDTO) throws ProductNotFoundException;
    ProductDTO findProductById(String id) throws ProductNotFoundException;
    List<ProductDTO> findAllProducts();
    ProductsPageDTO searchProducts(String keyword, int page, int size);
    ProductsPageDTO findProductsByCategoryId(Long categoryId, int page, int size);
    void deleteProductById(String id);
    void deleteAllProducts();
}
