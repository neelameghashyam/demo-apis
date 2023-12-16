package com.brodygaudel.demo.dto;

import java.util.List;

public record ProductsPageDTO(int page, int size, int totalPage, List<ProductDTO> products) {
}
