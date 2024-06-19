package com.ijala.view.product;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.util.form.FormUpdate;
import com.ijala.util.SearchIdBase;

import javax.swing.*;
import java.awt.*;

public class UpdateProductFrame extends SearchIdBase {
    private JPanel mainPanel;
    private ProductController productController;

    public UpdateProductFrame(ProductService productService) {
        super("Atualizar produto", productService);
        this.productController = new ProductController(productService);
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(Color.decode("#2B2B2B"));
        mainPanel.add(createSearchPanel(), "searchPanel");
        add(mainPanel);
    }

    @Override
    protected void handleProductFound(Product product) {
        SwingUtilities.invokeLater(() -> {
            showFormUpdate(product);
        });
    }

    private void showFormUpdate(Product product) {
        FormUpdate formUpdate = new FormUpdate(product, productController, this);
        JPanel formPanel = formUpdate.getFormPanel();

        // Ajustar o tamanho da janela para o formulário de atualização (600x700)
        setSize(600, 700);
        setLocationRelativeTo(null);

        // Adicionar o formulário ao painel principal
        mainPanel.add(formPanel, "formPanel");
        // Atualizar o layout para mostrar o formulário de atualização
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, "formPanel");
    }

    public static void main(String[] args) {
        ProductService productService = new ProductService(); // ou qualquer outra forma de obter o ProductService
        SwingUtilities.invokeLater(() -> {
            UpdateProductFrame updateProductFrame = new UpdateProductFrame(productService);
            updateProductFrame.setVisible(true);
        });
    }
}
