package com.ijala.util.component;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;
import com.ijala.util.FormBasePanel;

import javax.swing.*;
import java.awt.*;

public class FormUpdate extends FormBasePanel {
    private Produto produto;

    public FormUpdate(Produto produto) {
        this.produto = produto;
    }

    public JPanel getFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#2B2B2B"));

        JPanel formContent = new JPanel();
        formContent.setLayout(new BoxLayout(formContent, BoxLayout.Y_AXIS));
        formContent.setBackground(Color.decode("#2B2B2B"));
        formContent.add(Box.createVerticalStrut(30));
        formContent.add(createCustomContainer("Nome do Produto", true));
        formContent.add(Box.createVerticalStrut(20));
        formContent.add(createCustomContainer("Fornecedor", true));
        formContent.add(Box.createVerticalStrut(20));
        formContent.add(createCustomContainer("Categoria", true));
        formContent.add(Box.createVerticalStrut(20));
        formContent.add(createCustomContainer("Descrição", true));
        formContent.add(Box.createVerticalStrut(20));

        JPanel formSmallContent = new JPanel();
        formSmallContent.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        formSmallContent.setBackground(Color.decode("#2B2B2B"));
        formSmallContent.add(createSmallContainer("Preço", true));
        formSmallContent.add(createSmallContainer("Quantidade", true));
        formContent.add(Box.createVerticalStrut(0));
        formContent.add(formSmallContent);

        textFieldNome.setText(produto.getNome());
        textFieldFornecedor.setText(String.valueOf(produto.getFornecedorId()));
        textFieldCategoria.setText(String.valueOf(produto.getCategoriaId()));
        textFieldDescricao.setText(produto.getDescricao());
        textFieldPreco.setText(String.valueOf(produto.getPreco()));
        textFieldQuantidade.setText(String.valueOf(produto.getQuantidade()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.decode("#2B2B2B"));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setMaximumSize(new Dimension(200, 50));
        cancelButton.setBackground(Color.decode("#FF0000"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Atualização cancelada.");
        });

        JButton updateButton = new JButton("Atualizar");
        updateButton.setMaximumSize(new Dimension(200, 50));
        updateButton.setBackground(Color.decode("#2E56BE"));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.addActionListener(e -> {
            try {
                produto.setNome(textFieldNome.getText());
                produto.setFornecedorId(Integer.parseInt(textFieldFornecedor.getText()));
                produto.setCategoriaId(Integer.parseInt(textFieldCategoria.getText()));
                produto.setDescricao(textFieldDescricao.getText());
                produto.setPreco(Double.parseDouble(textFieldPreco.getText()));
                produto.setQuantidade(Integer.parseInt(textFieldQuantidade.getText()));

                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.atualizarProduto(produto);
                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: Preço e Quantidade devem ser números válidos.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex.getMessage());
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(updateButton);

        formContent.add(Box.createVerticalStrut(10));
        formContent.add(buttonPanel);
        formContent.add(Box.createVerticalStrut(40));
        panel.add(formContent);

        return panel;
    }
}
