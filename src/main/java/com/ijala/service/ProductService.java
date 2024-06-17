package com.ijala.service;

import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public Product searchProductById(int id) {
        return productDAO.searchProductById(id);
    }
}
