package com.example.product.web;

import com.example.product.dto.ProductDto;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = {"/product", "/product/list"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> listProducts(Model model){
        return productService.listAll();
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@PathVariable Long id){
        return productService.getById(id);
    }

    @RequestMapping(path = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto newProduct(@RequestBody @Validated ProductDto productDto) {
        return productService.save(productDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "product/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}