package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.repo.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repo;

    private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100.00);
    private static final String PRODUCT_DESCRIPTION = "A sample product";

    @Test
    public void listAllTest() {
        service.save(new ProductDto(null, "desc1", BIG_DECIMAL_100));
        service.save(new ProductDto(null, "desc2", BIG_DECIMAL_100));
        List<ProductDto> products = service.listAll();
        Assertions.assertThat(products).extracting(ProductDto::getDescription).contains("desc1","desc2");
    }

    @Test
    public void whenIdExistsThenGetByIdTest() {
        ProductDto current = service.save(new ProductDto(null, PRODUCT_DESCRIPTION, BIG_DECIMAL_100));
        ProductDto expected = service.getById(current.getId());
        Assertions.assertThat(current.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void whenNameAndEmailIsValidThenSaveTest() {
        ProductDto current = service.save(new ProductDto(null, PRODUCT_DESCRIPTION, BIG_DECIMAL_100));
        ProductDto expected = new ProductDto(current.getId(), PRODUCT_DESCRIPTION, BIG_DECIMAL_100);
        Assertions.assertThat(current).isEqualTo(expected);
    }

    @Test
    public void whenIdExistsThenDeleteProductTest() {
        ProductDto current = service.save(new ProductDto(null, PRODUCT_DESCRIPTION, BIG_DECIMAL_100));
        service.delete(current.getId());
        Assertions.assertThat(repo.findById(current.getId())).isNotPresent();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIdNotExistsThenNotDeleteProductTest() {
        service.delete(0L);
    }
}
