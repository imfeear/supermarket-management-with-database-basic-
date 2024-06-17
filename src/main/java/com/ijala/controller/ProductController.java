package com.ijala.controller;

import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;

import java.util.List;

public class ProductController {
    private ProductDAO productDAO;

    public ProductController() {
        this.productDAO = new ProductDAO();
    }

    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> listProducts() {
        return productDAO.listProducts();
    }

    public Product searchProductById(int id) {
        return productDAO.searchProductById(id);
    }

    public Product searchProductByName(String name) {
        return productDAO.searchProductByName(name);
    }

    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        try {
            productDAO.deleteProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
