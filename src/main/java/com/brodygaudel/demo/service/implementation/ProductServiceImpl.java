package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.ProductDTO;
import com.brodygaudel.demo.dto.ProductsPageDTO;
import com.brodygaudel.demo.entity.Category;
import com.brodygaudel.demo.entity.Product;
import com.brodygaudel.demo.exception.CategoryNotFoundException;
import com.brodygaudel.demo.exception.ProductNotFoundException;
import com.brodygaudel.demo.repository.CategoryRepository;
import com.brodygaudel.demo.repository.ProductRepository;
import com.brodygaudel.demo.service.ProductService;
import com.brodygaudel.demo.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Mappers mappers;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, Mappers mappers) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mappers = mappers;
    }

    @Override
    public ProductDTO saveProduct(@NotNull ProductDTO productDTO) throws CategoryNotFoundException {
        log.info("In saveProduct() :");
        Category category = categoryRepository.findById(productDTO.categoryId()).orElseThrow( () -> new CategoryNotFoundException("category not found"));
        Product product = mappers.fromProductDTO(productDTO);
        product.setCategory(category);
        product.setId(UUID.randomUUID().toString());
        Product productSaved = productRepository.save(product);
        log.info("product saved with id "+productSaved.getId());
        return mappers.fromProduct(productSaved);
    }

    @Override
    public ProductDTO updateProduct(@NotNull ProductDTO productDTO) throws ProductNotFoundException {
        log.info("In updateProduct() :");
        Product product = productRepository.findById(productDTO.id()).orElseThrow( () -> new ProductNotFoundException("product not found"));
        setProductItemsWithNewValuesFromProductDTO(product, productDTO);
        Product productUpdated = productRepository.save(product);
        log.info("product update");
        return mappers.fromProduct(productUpdated);
    }

    @Override
    public ProductDTO findProductById(String id) throws ProductNotFoundException {
        log.info("In findProductById()");
        Product product = productRepository.findById(id).orElseThrow( () -> new ProductNotFoundException("product not found"));
        log.info("product found");
        return mappers.fromProduct(product);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        log.info("In findAllProducts()");
        List<Product> products = productRepository.findAll();
        log.info(products.size()+" products found");
        return mappers.fromListOfProducts(products);
    }

    @Override
    public ProductsPageDTO searchProducts(String keyword, int page, int size) {
        log.info("In searchProducts()");
        Page<Product> productPage = productRepository.search("%"+keyword+"%", PageRequest.of(page, size));
        log.info(productPage.getContent().size()+" product(s) found");
        return new ProductsPageDTO(page, size, productPage.getTotalPages(), mappers.fromListOfProducts(productPage.getContent()));
    }

    @Override
    public ProductsPageDTO findProductsByCategoryId(Long categoryId, int page, int size) {
        log.info("In findProductsByCategoryId()");
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, PageRequest.of(page, size));
        log.info(productPage.getContent().size()+" product(s) found");
        return new ProductsPageDTO(page, size, productPage.getTotalPages(), mappers.fromListOfProducts(productPage.getContent()));
    }

    @Override
    public void deleteProductById(String id) {
        log.info("In deleteProductById()");
        productRepository.deleteById(id);
        log.info("product deleted");
    }

    @Override
    public void deleteAllProducts() {
        log.info("In deleteAllProducts()");
        productRepository.deleteAll();
        log.info("all products deleted");
    }

    private void setProductItemsWithNewValuesFromProductDTO(@NotNull Product product, @NotNull ProductDTO productDTO){
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setBarcode(product.getBarcode());
        product.setQuantity(product.getQuantity());
        product.setDescription(product.getDescription());
    }
}
