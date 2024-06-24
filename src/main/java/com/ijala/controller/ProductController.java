package com.ijala.controller;

import com.ijala.model.product.Product;
import com.ijala.model.stock.Stock;
import com.ijala.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> listProducts() {
        return productService.listProducts();
    }

    public void registerProduct(Product product) {
        try {
            productService.insertProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try {
            productService.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateQuantity(Product product) {
        try {
            productService.updateQuantity(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        try {
            productService.deleteProduct(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int productId) {
        return productService.searchProductById(productId);
    }

    public List<Stock> listFormattedStock() {
        return productService.listFormattedStock();
    }

}
