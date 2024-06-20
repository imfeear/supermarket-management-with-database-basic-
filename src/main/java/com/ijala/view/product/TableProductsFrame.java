package com.ijala.view.product;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.util.ButtonUtil;
import com.ijala.util.panel.TablePanel;
import com.ijala.view.buy.SearchIdForBuy;
import com.ijala.view.sell.SearchIdForSell;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TableProductsFrame extends JFrame {
    private TablePanel tablePanel;
    private ProductController productController;
    private ProductService productService;

    public TableProductsFrame() {
        super("Produtos");
        productService = new ProductService(); // Instancia ProductService
        productController = new ProductController(productService); // Passa ProductService para ProductController
        initComponents();
        loadProducts();
    }

    // Método para retornar o ProductService
    public ProductService getService() {
        return productService;
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(43, 43, 43));
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 40));

        JLabel titleLabel = new JLabel("Produtos Cadastrados");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        buttonPanel.setBackground(new Color(43, 43, 43));

        ButtonUtil buyButton = new ButtonUtil("Comprar", e -> new SearchIdForBuy(new ProductService()).setVisible(true));
        ButtonUtil sellButton = new ButtonUtil("Vender", e -> new SearchIdForSell(new ProductService()).setVisible(true));
        ButtonUtil registerButton = new ButtonUtil("Cadastrar", e -> new RegisterProductFrame().setVisible(true));
        ButtonUtil updateButton = new ButtonUtil("Atualizar", e -> {
            ProductService productService = getService();
            new UpdateProductFrame(productService).setVisible(true);
        });
        ButtonUtil deleteButton = new ButtonUtil("Excluir", e -> new DeleteProductFrame().setVisible(true));
        deleteButton.setBackground(Color.RED);

        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        String[] columnNames = {"ID", "Nome", "Descrição", "Quantidade", "Preço", "Categoria ID", "Fornecedor ID"};
        int[] columnWidths = {80, 300, 500, 150, 150, 150, 150};

        tablePanel = new TablePanel(columnNames, columnWidths);
        JScrollPane scrollPane = tablePanel.getScrollPane();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(topPanel, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void loadProducts() {
        DefaultTableModel model = tablePanel.getModel();
        model.setRowCount(0); // Limpa os dados da tabela
        List<Product> products = productController.listProducts();
        for (Product product : products) {
            model.addRow(new Object[]{
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getCategoryId(),
                    product.getSupplierId()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TableProductsFrame tableProductsFrame = new TableProductsFrame();
            tableProductsFrame.setVisible(true);
        });
    }
}
