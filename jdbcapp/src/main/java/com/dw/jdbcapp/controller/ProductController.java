package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Department;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/find-all-products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("products/{productNumber}")
    public Product getProductByNumber(@PathVariable String productNumber) {
        return productService.getProductByNumber(productNumber);
    }

    @PostMapping("/post/product")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/post/productList")
      public List<Product> saveProductList(
            @RequestBody List<Product> productList) {
        return productService.saveProdudctList(productList);
    }
}