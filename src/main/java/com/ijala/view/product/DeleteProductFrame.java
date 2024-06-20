package com.ijala.view.product;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.util.SearchIdBase;

import javax.swing.*;

public class DeleteProductFrame extends SearchIdBase {
    private ProductController productController;

    public DeleteProductFrame() {
        super("Excluir Produto", new ProductService());
        ProductService productService = new ProductService(); // Instancia um ProductService
        productController = new ProductController(productService); // Passa o ProductService para o ProductController
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
    }

    @Override
    protected void handleProductFound(Product product) {
        SwingUtilities.invokeLater(() -> {
            int option = JOptionPane.showConfirmDialog(this,
                    "Você deseja realmente excluir o produto:\n\n" +
                            "ID: " + product.getId() + "\n" +
                            "Nome: " + product.getName() + "\n" +
                            "Descrição: " + product.getDescription() + "\n\n" +
                            "Esta ação não pode ser desfeita.", "Confirmação",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                productController.deleteProduct(product.getId());
                JOptionPane.showMessageDialog(this,
                        "Produto excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else if (option == JOptionPane.NO_OPTION) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteProductFrame deleteProductFrame = new DeleteProductFrame();
            deleteProductFrame.setVisible(true);
        });
    }
}
