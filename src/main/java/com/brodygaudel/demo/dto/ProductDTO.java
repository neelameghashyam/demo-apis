package com.brodygaudel.demo.dto;

public record ProductDTO(String id,
                         String name,
                         String description,
                         String barcode,
                         Double price,
                         Integer quantity,
                         Long categoryId) {
}
