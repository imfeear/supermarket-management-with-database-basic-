package com.ijala.controller;

import com.ijala.model.product.Product;
import com.ijala.service.ProductService;

import java.sql.Date;
import java.util.List;

public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addStock(Product product, int quantity, Date timestamp) {
        try {
            productService.addStock(product, quantity, timestamp);
        } catch (Exception e) {
            e.printStackTrace();
            // Aqui você pode tratar o erro ou lançar uma exceção adequada
        }
    }

//    public void addProduct(Product product) {
//        try {
//            productService.addProduct(product);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public List<Product> listProducts() {
        return productService.listProducts();
    }

    public void updateProduct(Product product) {
        try {
            productService.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sellStock(int productId, int quantity) throws Exception {
        productService.sellStock(productId, quantity);
    }

    public void updateQuantity(Product product) throws Exception {
        if (product != null) {
            productService.updateQuantity(product);
        } else {
            throw new Exception("Product cannot be null.");
        }
    }

    public void deleteProduct(int id) {
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
