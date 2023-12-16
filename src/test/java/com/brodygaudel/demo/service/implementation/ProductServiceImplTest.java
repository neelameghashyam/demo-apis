package com.brodygaudel.demo.service.implementation;

import com.brodygaudel.demo.dto.ProductDTO;
import com.brodygaudel.demo.dto.ProductsPageDTO;
import com.brodygaudel.demo.entity.Category;
import com.brodygaudel.demo.entity.Product;
import com.brodygaudel.demo.exception.CategoryNotFoundException;
import com.brodygaudel.demo.exception.ProductNotFoundException;
import com.brodygaudel.demo.repository.CategoryRepository;
import com.brodygaudel.demo.repository.ProductRepository;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private Mappers mappers;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(
                productRepository,
                categoryRepository,
                mappers
        );
    }

    @Test
    void saveProduct() throws CategoryNotFoundException {
        String id = UUID.randomUUID().toString();
        Category category = new Category(1L, "name", "description", null);
        Product product = Product.builder().id(id).quantity(9).price(56.8).name("name").description("description").barcode("198561235").build();
        ProductDTO productDTO = new ProductDTO(id, "name", "description", "987652145", 69.75, 87, category.getId());
        Product productSaved = Product.builder().id(id).quantity(9).price(56.8).name("name").description("description").barcode("198561235").category(category).build();

        when(categoryRepository.findById(productDTO.categoryId())).thenReturn(Optional.of(category));
        when(mappers.fromProductDTO(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(productSaved);
        when(mappers.fromProduct(productSaved)).thenReturn(productDTO);

        ProductDTO response = productService.saveProduct(productDTO);
        assertNotNull(response);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void updateProduct() throws ProductNotFoundException {
        ProductDTO productDTO = new ProductDTO("id", "name","description", "barcode", 56.0, 10, 1L);
        Product product = Product.builder().id("id").quantity(9).price(56.8).name("name").description("description").barcode("198561235").build();
        when(productRepository.findById(productDTO.id())).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(new Product());
        when(mappers.fromProduct(any())).thenReturn(
                new ProductDTO("id", "new name","new description", "new barcode", 76.0, 90, 1L)
        );

        ProductDTO response = productService.updateProduct(productDTO);
        assertNotNull(response);
        assertEquals("id", response.id());
    }

    @Test
    void findProductById() throws ProductNotFoundException {
        Product product = Product.builder().id("id").quantity(9).price(56.8).name("name").description("description").barcode("198561235").build();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(mappers.fromProduct(product)).thenReturn(
                new ProductDTO("id", "new name","new description", "new barcode", 76.0, 90, 1L)
        );
        ProductDTO response = productService.findProductById("id");
        assertNotNull(response);
        assertEquals("id", response.id());
    }

    @Test
    void findAllProducts() {
        Category category = new Category(1L, "name", "description", null);
        Product product1 = Product.builder().id("id1").quantity(9).price(16.8).name("name 1").description("description 1").barcode("barcode1").category(category).build();
        Product product2 = Product.builder().id("id2").quantity(6).price(26.8).name("name 2").description("description 2").barcode("barcode2").category(category).build();
        List<Product> productList = List.of(product1, product2);

        ProductDTO productDTO1 = new ProductDTO("id1", "name 1","description 1", "barcode1", 56.0, 10, 1L);
        ProductDTO productDTO2 = new ProductDTO("id2", "name 2","description 2", "barcode2", 56.0, 10, 1L);
        List<ProductDTO> productDTOList = List.of(productDTO1, productDTO2);

        when(productRepository.findAll()).thenReturn(productList);
        when(mappers.fromListOfProducts(productList)).thenReturn(productDTOList);

        List<ProductDTO> response = productService.findAllProducts();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void searchProducts() {
        String keyword = "name";
        int page = 0;
        int size = 2;
        Category category = new Category(1L, "name", "description", null);
        Product product1 = Product.builder().id("id1").quantity(9).price(16.8).name("name 1").description("description 1").barcode("barcode1").category(category).build();
        Product product2 = Product.builder().id("id2").quantity(6).price(26.8).name("name 2").description("description 2").barcode("barcode2").category(category).build();
        List<Product> productList = List.of(product1, product2);
        ProductDTO productDTO1 = new ProductDTO("id1", "name 1","description 1", "barcode1", 56.0, 10, 1L);
        ProductDTO productDTO2 = new ProductDTO("id2", "name 2","description 2", "barcode2", 56.0, 10, 1L);
        List<ProductDTO> productDTOList = List.of(productDTO1, productDTO2);

        when(productRepository.search("%"+keyword+"%", PageRequest.of(page, size))).thenReturn(
                        new PageImpl<>(productList, PageRequest.of(page, size), 1)
        );
        when(mappers.fromListOfProducts(productList)).thenReturn(productDTOList);

        ProductsPageDTO response = productService.searchProducts(keyword, page, size);
        assertNotNull(response);
        assertFalse(response.products().isEmpty());
        assertEquals(2, response.products().size());
        assertEquals(1, response.totalPage());
    }

    @Test
    void findProductsByCategoryId() {
        int page = 0;
        int size = 2;
        Category category = new Category(1L, "name", "description", null);
        Product product1 = Product.builder().id("id1").quantity(9).price(16.8).name("name 1").description("description 1").barcode("barcode1").category(category).build();
        Product product2 = Product.builder().id("id2").quantity(6).price(26.8).name("name 2").description("description 2").barcode("barcode2").category(category).build();
        List<Product> productList = List.of(product1, product2);
        ProductDTO productDTO1 = new ProductDTO("id1", "name 1","description 1", "barcode1", 56.0, 10, 1L);
        ProductDTO productDTO2 = new ProductDTO("id2", "name 2","description 2", "barcode2", 56.0, 10, 1L);
        List<ProductDTO> productDTOList = List.of(productDTO1, productDTO2);

        when(productRepository.findByCategoryId(category.getId(), PageRequest.of(page, size))).thenReturn(
                new PageImpl<>(productList, PageRequest.of(page, size), 1)
        );
        when(mappers.fromListOfProducts(productList)).thenReturn(productDTOList);
        ProductsPageDTO response = productService.findProductsByCategoryId(category.getId(), page, size);
        assertNotNull(response);
        assertFalse(response.products().isEmpty());
        assertEquals(2,response.products().size());
        assertEquals(1, response.totalPage());
    }

    @Test
    void deleteProductById() {
        String productId = "productId";
        productService.deleteProductById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void deleteAllProducts() {
        productService.deleteAllProducts();
        verify(productRepository, times(1)).deleteAll();
    }
}