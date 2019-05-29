package com.example.product.repo;

import com.example.product.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100.00);
    private static final String PRODUCT_DESCRIPTION = "A sample product";
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void givenProductWhenSaveTest() {
        Product productSaved = productRepository.save(new Product(null, PRODUCT_DESCRIPTION, BIG_DECIMAL_100));

        Product newProduct = productRepository.findById(productSaved.getId()).orElse(null);

        Assert.assertNotNull(newProduct);
        Assert.assertEquals(productSaved.getId(), newProduct.getId());
        Assert.assertEquals(PRODUCT_DESCRIPTION, newProduct.getDescription());
        Assert.assertEquals(BIG_DECIMAL_100.compareTo(newProduct.getPrice()), 0);
    }
}