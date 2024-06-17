package com.ijala.view.sell;

import com.ijala.controller.MovementController;
import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.util.ButtonUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchIdForSell extends JFrame {
    private JTextField idField;
    private ProductService productService;

    public SearchIdForSell(ProductService productService) {
        super("Buscar Produto");
        this.productService = productService;
        initComponents();
    }

    private void initComponents() {
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

    private JPanel createSearchPanel() {
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
            Product product = productService.searchProductById(id);
            if (product != null) {
                dispose();
                MovementController movementController = new MovementController();
                SellProductFrame sellProductFrame = new SellProductFrame(product, movementController);
                sellProductFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número inteiro.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductService productService = new ProductService();
            SearchIdForSell searchIdForSell = new SearchIdForSell(productService);
            searchIdForSell.setVisible(true);
        });
    }
}
