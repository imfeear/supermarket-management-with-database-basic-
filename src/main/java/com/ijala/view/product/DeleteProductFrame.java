package com.ijala.view.product;

import javax.swing.*;

import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;
import com.ijala.util.ButtonUtil;

import java.awt.*;
import java.awt.event.ActionEvent;

public class DeleteProductFrame extends JFrame {
    private ProductDAO productDAO;
    private JTextField idField;
    private JPanel mainPanel;

    public DeleteProductFrame() {
        super("Excluir Produto");
        productDAO = new ProductDAO();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300); // Definir o tamanho inicial da janela
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#2B2B2B"));

        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel searchPanel = createSearchPanel();
        mainPanel.add(searchPanel, "searchPanel");

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
        idField.add(Box.createVerticalStrut(20));

        ButtonUtil searchButton = new ButtonUtil("Buscar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });

        searchPanel.add(Box.createVerticalStrut(50));
        searchPanel.add(label);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(idField);
        searchPanel.add(Box.createVerticalStrut(40));
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private void delete() {
        String idText = idField.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira um ID de produto válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            Product product = productDAO.searchProductById(id);
            if (product != null) {
                int option = JOptionPane.showConfirmDialog(this,
                        "Você deseja realmente excluir o produto:\n\n" +
                                "ID: " + product.getId() + "\n" +
                                "Nome: " + product.getName() + "\n" +
                                "Descrição: " + product.getDescription() + "\n\n" +
                                "Esta ação não pode ser desfeita.", "Confirmação",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    productDAO.deleteProduct(id);
                    JOptionPane.showMessageDialog(this,
                            "Produto excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    idField.setText("");
                    // Troca de Tela
                    TableProductsFrame tableProductsFrame = new TableProductsFrame();
                    tableProductsFrame.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Nenhum produto encontrado com o ID informado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira um ID de produto válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteProductFrame deleteProductFrame = new DeleteProductFrame();
            deleteProductFrame.setVisible(true);
        });
    }
}