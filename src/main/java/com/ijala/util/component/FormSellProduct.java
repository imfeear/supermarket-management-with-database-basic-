package com.ijala.util.component;

import javax.swing.*;
import java.awt.*;

public class FormSellProduct {

    private JPanel formPanel;
    private JTextField textFieldNome;
    private JTextField textFieldFornecedor;
    private JTextField textFieldCategoria;
    private JTextField textFieldDescricao;
    private JTextField textFieldPreco;
    private JTextField textFieldQuantidade;
    private JButton venderButton;

    public FormSellProduct() {
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.decode("#2B2B2B"));

        // Nome do Produto
        textFieldNome = new JTextField(20);
        formPanel.add(createFormRow("Nome do Produto", textFieldNome));

        // Fornecedor
        textFieldFornecedor = new JTextField(20);
        formPanel.add(createFormRow("Fornecedor", textFieldFornecedor));

        // Categoria
        textFieldCategoria = new JTextField(20);
        formPanel.add(createFormRow("Categoria", textFieldCategoria));

        // Descrição
        textFieldDescricao = new JTextField(20);
        formPanel.add(createFormRow("Descrição", textFieldDescricao));

        // Preço
        textFieldPreco = new JTextField(20);
        formPanel.add(createFormRow("Preço", textFieldPreco));

        // Quantidade
        textFieldQuantidade = new JTextField(20);
        formPanel.add(createFormRow("Quantidade", textFieldQuantidade));

        // Botão Vender
        venderButton = new JButton("Vender");
        venderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        venderButton.setBackground(Color.decode("#2E56BE"));
        venderButton.setForeground(Color.WHITE);
        venderButton.setFont(new Font("Arial", Font.BOLD, 14));
        venderButton.setPreferredSize(new Dimension(200, 50));

        // Adiciona o botão ao painel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#2B2B2B"));
        buttonPanel.add(venderButton);
        formPanel.add(Box.createVerticalStrut(20)); // Espaço antes do botão
        formPanel.add(buttonPanel);
    }

    private JPanel createFormRow(String label, JTextField textField) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rowPanel.setBackground(Color.decode("#2B2B2B"));

        JLabel jLabel = new JLabel(label);
        jLabel.setForeground(Color.WHITE);
        jLabel.setPreferredSize(new Dimension(150, 30));
        jLabel.setFont(new Font("Arial", Font.BOLD, 15));

        textField.setPreferredSize(new Dimension(200, 30));

        rowPanel.add(jLabel);
        rowPanel.add(textField);

        return rowPanel;
    }

    public JPanel getFormPanel() {
        return formPanel;
    }

    public JButton getVenderButton() {
        return venderButton;
    }

    public String getNome() {
        return textFieldNome.getText();
    }

    public String getFornecedor() {
        return textFieldFornecedor.getText();
    }

    public String getCategoria() {
        return textFieldCategoria.getText();
    }

    public String getDescricao() {
        return textFieldDescricao.getText();
    }

    public double getPreco() {
        return Double.parseDouble(textFieldPreco.getText());
    }

    public int getQuantidade() {
        return Integer.parseInt(textFieldQuantidade.getText());
    }

    public String getNomeProduto() {
        return textFieldNome.getText();
    }
}
