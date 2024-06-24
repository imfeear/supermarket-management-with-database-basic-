package com.ijala.view.buy;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.util.SearchIdBase;

import javax.swing.*;

public class SearchIdForBuy extends SearchIdBase {

    public SearchIdForBuy(ProductController productController) {
        super("Buscar Produto", productController);
    }

    @Override
    protected void handleProductFound(Product product) {
        SwingUtilities.invokeLater(() -> {
            BuyProductFrame buyProductFrame = new BuyProductFrame();
            buyProductFrame.setProductDetails(
                    product.getId(),
                    product.getName(),
                    String.valueOf(product.getSupplierId()),
                    String.valueOf(product.getPrice())
            );
            buyProductFrame.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductController productController = new ProductController(new ProductService());
            SearchIdForBuy searchIdForBuy = new SearchIdForBuy(productController);
            searchIdForBuy.setVisible(true);
        });
    }
}
