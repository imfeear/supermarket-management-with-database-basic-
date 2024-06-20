package com.ijala.service;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;
import com.ijala.model.stock.Stock;
import com.ijala.model.stock.StockDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;
    private StockDAO stockDAO;
    private ProductController productController;

    public ProductService() {
        this.productDAO = new ProductDAO();
        this.stockDAO = new StockDAO(productDAO);
        this.productController = new ProductController(this);
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


//    public void addStock(Product product, int quantity, Date entryDate) {
//        Stock stock = stockDAO.getStockByProductId(product.getId());
//        if (stock != null) {
//            stock.setInitialStock(stock.getInitialStock() + quantity);
//            stock.setFinalStock(stock.getFinalStock() + quantity);
//            stockDAO.updateStock(oldStock, newStock); // Corrigido aqui
//        } else {
//            stock = new Stock(product, quantity, entryDate, null, quantity);
//            stockDAO.addInStock(stock);
//        }
//    }

    public void addStock(Product product, int quantity, Date timestamp) {
        try {
            productController.addStock(product, quantity, timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sellStock(int productId, int quantity) throws Exception {
        Stock stock = stockDAO.getStockByProductId(productId);
        if (stock != null) {
            Stock newStock = new Stock(
                    stock.getProduct(),
                    stock.getInitialStock(),
                    stock.getEntry(),
                    new Date(System.currentTimeMillis()),
                    stock.getFinalStock() - quantity
            );
            newStock.setId(stock.getId());
            stockDAO.updateStock(stock, newStock);
        } else {
            throw new Exception("Produto não encontrado no estoque ao realizar venda: " + productId);
        }
    }
}
