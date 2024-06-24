package com.ijala.service;

import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;
import com.ijala.model.stock.Stock;
import com.ijala.model.stock.StockDAO;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;
    private final StockDAO stockDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
        this.stockDAO = new StockDAO(this);
    }

    public void insertProduct(Product product) throws SQLException {
        productDAO.insertProduct(product);
    }

    public List<Product> listProducts() {
        return productDAO.listProducts();
    }

    public Product searchProductById(int id) {
        return productDAO.searchProductById(id);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void updateQuantity(Product product) {
        productDAO.updateQuantity(product);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    // Método para listar produtos em estoque com as datas já formatados
    public List<Stock> listFormattedStock() {
        return stockDAO.listProductInStock();
    }
}
