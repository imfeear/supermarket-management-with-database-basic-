package com.ijala.util.component;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;
import com.ijala.util.FormBasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormRegisterProduct extends FormBasePanel {

    public JPanel getFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#2B2B2B"));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(50, 0, 50, 0)
        ));

        JPanel formContent = new JPanel();
        formContent.setLayout(new BoxLayout(formContent, BoxLayout.Y_AXIS));
        formContent.setBackground(Color.decode("#2B2B2B"));
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
        formContent.add(formSmallContent);

        JButton button = new JButton("Cadastrar");
        button.setPreferredSize(new Dimension(460, 50));
        button.setMaximumSize(new Dimension(460, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.decode("#2E56BE"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = textFieldNome.getText();
                    String fornecedor = textFieldFornecedor.getText();
                    String categoria = textFieldCategoria.getText();
                    String descricao = textFieldDescricao.getText();
                    double preco = Double.parseDouble(textFieldPreco.getText());
                    int quantidade = Integer.parseInt(textFieldQuantidade.getText());

                    if (nome.isEmpty() || fornecedor.isEmpty() || categoria.isEmpty() || descricao.isEmpty() || preco <= 0 || quantidade <= 0) {
                        throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
                    }

                    Produto produto = new Produto(nome, descricao, quantidade, preco, Integer.parseInt(categoria), Integer.parseInt(fornecedor));

                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produtoDAO.adicionarProduto(produto);
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Preço e Quantidade devem ser números válidos.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
                }
            }
        });

        formContent.add(Box.createVerticalStrut(50));
        formContent.add(button);
        panel.add(formContent);

        return panel;
    }
}
