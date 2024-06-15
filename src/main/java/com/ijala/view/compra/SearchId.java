package com.ijala.view.compra;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;

import javax.swing.*;
import java.awt.*;

public class SearchId extends JFrame {
    private JPanel mainPanel;
    public SearchId() {
        super("Procurar por ID");
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

        JTextField idField = new JTextField();
        idField.setMaximumSize(new Dimension(200, 40));
        idField.setAlignmentX(Component.CENTER_ALIGNMENT);
        idField.add(Box.createVerticalStrut(20));

        JButton searchButton = new JButton("Buscar");
        searchButton.setMaximumSize(new Dimension(200, 50));
        searchButton.setBackground(Color.decode("#2E56BE"));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(e -> {
            String idText = idField.getText();
            try {
                int id = Integer.parseInt(idText);
                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produto = produtoDAO.buscarProdutoPorId(id);
                if (produto != null) {
                    new ComprarProduto(produto.getId(),produto.getNome(), String.valueOf(produto.getFornecedorId()), String.valueOf(produto.getCategoriaId()), produto.getDescricao(), String.valueOf(produto.getPreco()), String.valueOf(produto.getQuantidade())).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número inteiro.");
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
}
