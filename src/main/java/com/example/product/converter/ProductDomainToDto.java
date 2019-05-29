package com.example.product.converter;

import com.example.product.domain.Product;
import com.example.product.dto.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDomainToDto implements Converter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}