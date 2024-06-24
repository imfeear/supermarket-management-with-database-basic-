package com.ijala.view.sell;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.util.SearchIdBase;

import javax.swing.*;

public class SearchIdForSell extends SearchIdBase {
    public SearchIdForSell(ProductController productController) {
        super("Buscar Produto", productController);
    }

    @Override
    protected void handleProductFound(Product product) {
        SwingUtilities.invokeLater(() -> {
            SellProductFrame sellProductFrame = new SellProductFrame(product);
            sellProductFrame.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductController productController = new ProductController(new ProductService());
            SearchIdForSell searchIdForSell = new SearchIdForSell(productController);
            searchIdForSell.setVisible(true);
        });
    }
}
