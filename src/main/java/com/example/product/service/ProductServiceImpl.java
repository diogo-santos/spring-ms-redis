package com.example.product.service;

import com.example.product.converter.ProductDomainToDto;
import com.example.product.domain.Product;
import com.example.product.dto.ProductDto;
import com.example.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductDomainToDto domainToDto;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDomainToDto domainToDto) {
        this.productRepository = productRepository;
        this.domainToDto = domainToDto;
    }

    @Override
    public List<ProductDto> listAll() {
        List<ProductDto> products = new ArrayList<>();
        productRepository
                .findAll()
                .forEach(p -> products.add(domainToDto.convert(p)));
        return products;
    }

    @Override
    public ProductDto getById(Long id) {
        return productRepository
                .findById(id)
                .map(domainToDto::convert)
                .orElse(null);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product productSaved = productRepository.save(new Product(null, productDto.getDescription(), productDto.getPrice()));
        return domainToDto.convert(productSaved);
    }

    @Override
    public void delete(Long id) {
        productRepository
                .findById(id)
                .orElseThrow(()->new NoSuchElementException("Product does not exist: " + id));
        productRepository.deleteById(id);
    }
}