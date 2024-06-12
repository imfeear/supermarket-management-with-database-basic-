package com.ijala.util.component;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;
import com.ijala.util.FormBase;

import javax.swing.*;
import java.awt.*;

public class FormAtualizacao extends FormBase {
    private Produto produto;

    public FormAtualizacao(Produto produto) {
        this.produto = produto;
    }

    public JPanel getFormPanel() {
        JPanel containerForm = new JPanel();
        containerForm.setLayout(new BoxLayout(containerForm, BoxLayout.Y_AXIS));
        containerForm.setBackground(Color.decode("#2B2B2B"));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#2B2B2B"));
        form.add(Box.createVerticalStrut(30));
        form.add(createCustomContainer("Nome do Produto", true));
        form.add(Box.createVerticalStrut(20));
        form.add(createCustomContainer("Fornecedor", true));
        form.add(Box.createVerticalStrut(20));
        form.add(createCustomContainer("Categoria", true));
        form.add(Box.createVerticalStrut(20));
        form.add(createCustomContainer("Descrição", true));
        form.add(Box.createVerticalStrut(20));

        JPanel smallContainersPanel = new JPanel();
        smallContainersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(createSmallContainer("Preço", true));
        smallContainersPanel.add(createSmallContainer("Quantidade", true));
        form.add(Box.createVerticalStrut(0));
        form.add(smallContainersPanel);

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

        form.add(Box.createVerticalStrut(10));
        form.add(buttonPanel);
        form.add(Box.createVerticalStrut(40));
        containerForm.add(form);

        return containerForm;
    }
}
