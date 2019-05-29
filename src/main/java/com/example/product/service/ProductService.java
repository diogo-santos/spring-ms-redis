package com.example.product.service;

import com.example.product.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> listAll();
    ProductDto getById(Long id);
    ProductDto save(ProductDto productDto);
    void delete(Long id);
}