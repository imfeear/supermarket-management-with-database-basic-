package com.ijala.view.sell;

import com.ijala.controller.MovementController;
import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.util.SearchIdBase;

import javax.swing.*;

public class SearchIdForSell extends SearchIdBase {
    public SearchIdForSell(ProductService productService) {
        super("Buscar Produto", productService);
    }

    @Override
    protected void handleProductFound(Product product) {
        SwingUtilities.invokeLater(() -> {
            MovementController movementController = new MovementController();
            SellProductFrame sellProductFrame = new SellProductFrame(product, movementController);
            sellProductFrame.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductService productService = new ProductService();
            SearchIdForSell searchIdForSell = new SearchIdForSell(productService);
            searchIdForSell.setVisible(true);
        });
    }
}
