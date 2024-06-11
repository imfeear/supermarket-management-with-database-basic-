package org.supermercado.views;

import org.supermercado.components.CustomComponents;
import org.supermercado.components.SmallCustomComponents;
import org.supermercado.ProdutoDAO;
import org.supermercado.Produto;

import javax.swing.*;
import java.awt.*;

public class AtualizarProduto extends JFrame {
    private JTextField idField;
    private JTextField nomeField;
    private JTextField descricaoField;
    private JTextField quantidadeField;
    private JTextField precoField;
    private JTextField categoriaField;
    private JTextField fornecedorField;

    public AtualizarProduto() {
        super("Atualizar informações do produto");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#2B2B2B"));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#2B2B2B"));
        form.add(CustomComponents.create("Nome do Produto", true));
        form.add(Box.createVerticalStrut(20));
        form.add(CustomComponents.create("Fornecedor", true));
        form.add(Box.createVerticalStrut(20));
        form.add(CustomComponents.create("Categoria", true));
        form.add(Box.createVerticalStrut(20));
        form.add(CustomComponents.create("Descrição", true));
        form.add(Box.createVerticalStrut(20));

        JPanel smallContainersPanel = new JPanel();
        smallContainersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(SmallCustomComponents.create("Preço", true));
        smallContainersPanel.add(SmallCustomComponents.create("Quantidade", true));
        form.add(Box.createVerticalStrut(0));
        form.add(smallContainersPanel);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 40));
        buttonsPanel.setBackground(Color.decode("#2B2B2B"));

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setPreferredSize(new Dimension(200, 50));
        cancelarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelarButton.setBackground(Color.RED);
        cancelarButton.setForeground(Color.WHITE);
        cancelarButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelarButton.addActionListener(e -> dispose());

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.setPreferredSize(new Dimension(200, 50));
        atualizarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        atualizarButton.setBackground(Color.decode("#2E56BE"));
        atualizarButton.setForeground(Color.WHITE);
        atualizarButton.setFont(new Font("Arial", Font.BOLD, 14));
        atualizarButton.addActionListener(e -> {
            atualizarProduto();
        });

        buttonsPanel.add(cancelarButton);
        buttonsPanel.add(atualizarButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void atualizarProduto() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nome = nomeField.getText();
            String descricao = descricaoField.getText();
            int quantidade = Integer.parseInt(quantidadeField.getText());
            double preco = Double.parseDouble(precoField.getText());
            int categoriaId = Integer.parseInt(categoriaField.getText());
            int fornecedorId = Integer.parseInt(fornecedorField.getText());

            Produto produto = new Produto(nome, descricao, quantidade, preco, categoriaId, fornecedorId);
            produto.setId(id);

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.atualizarProduto(produto);

            JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro nos dados inseridos. Verifique e tente novamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AtualizarProduto tela = new AtualizarProduto();
            tela.setVisible(true);
        });
    }
}
