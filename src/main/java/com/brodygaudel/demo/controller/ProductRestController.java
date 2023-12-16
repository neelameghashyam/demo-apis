package com.brodygaudel.demo.controller;

import com.brodygaudel.demo.dto.ProductDTO;
import com.brodygaudel.demo.dto.ProductsPageDTO;
import com.brodygaudel.demo.exception.CategoryNotFoundException;
import com.brodygaudel.demo.exception.ProductNotFoundException;
import com.brodygaudel.demo.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) throws CategoryNotFoundException{
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) throws ProductNotFoundException{
        return productService.updateProduct(productDTO);
    }

    @GetMapping("/get/{id}")
    public ProductDTO findProductById(@PathVariable String id) throws ProductNotFoundException{
        return productService.findProductById(id);
    }

    @GetMapping("/list")
    public List<ProductDTO> findAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/search")
    public ProductsPageDTO searchProducts(@RequestParam( defaultValue = "") String keyword,
                                          @RequestParam( defaultValue = "0") int page,
                                          @RequestParam( defaultValue = "10") int size){
        return productService.searchProducts(keyword, page, size);
    }

    @GetMapping("/find/{categoryId}/{page}/{size}")
    public ProductsPageDTO findProductsByCategoryId(@PathVariable(name = "categoryId") Long categoryId,
                                                    @PathVariable(name = "page") int page,
                                                    @PathVariable(name = "size") int size){
        return productService.findProductsByCategoryId(categoryId, page, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable String id){
        productService.deleteProductById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAllProducts(){
        productService.deleteAllProducts();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
