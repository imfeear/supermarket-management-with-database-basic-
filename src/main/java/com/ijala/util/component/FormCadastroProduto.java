package com.ijala.util.component;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;
import com.ijala.util.FormBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCadastroProduto extends FormBase {

    public JPanel getFormPanel() {
        JPanel containerForm = new JPanel();
        containerForm.setLayout(new BoxLayout(containerForm, BoxLayout.Y_AXIS));
        containerForm.setBackground(Color.decode("#2B2B2B"));
        containerForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(50, 0, 50, 0)
        ));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#2B2B2B"));
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

        JButton button = new JButton("Cadastrar");
        button.setPreferredSize(new Dimension(460, 50));
        button.setMaximumSize(new Dimension(460, 50));
        button.setMinimumSize(new Dimension(460, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.decode("#2E56BE"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
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

        form.add(Box.createVerticalStrut(50));
        form.add(button);
        containerForm.add(form);

        return containerForm;
    }
}
