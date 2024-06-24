package com.ijala.util;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class SearchIdBase extends JFrame {
    protected JTextField idField;
    protected ProductController productController;

    public SearchIdBase(String title, ProductController productController) {
        super(title);
        this.productController = productController;
        initComponents();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#2B2B2B"));

        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(Color.decode("#2B2B2B"));
        mainPanel.add(createSearchPanel(), "searchPanel");

        add(mainPanel);
    }

    public JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel("Insira o ID do Produto:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        idField = new JTextField();
        idField.setMaximumSize(new Dimension(200, 40));
        idField.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonUtil searchButton = new ButtonUtil("Buscar", this::searchProduct);

        searchPanel.add(Box.createVerticalStrut(50));
        searchPanel.add(label);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(idField);
        searchPanel.add(Box.createVerticalStrut(40));
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private void searchProduct(ActionEvent e) {
        String idText = idField.getText();
        try {
            int id = Integer.parseInt(idText);
            Product product = productController.getProductById(id);
            if (product != null) {
                handleProductFound(product);
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número inteiro.", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected abstract void handleProductFound(Product product);
}