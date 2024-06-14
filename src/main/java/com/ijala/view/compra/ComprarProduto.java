package com.ijala.view.compra;

import com.ijala.util.component.FormCustomContent;
import com.ijala.util.component.FormCustomSmallContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComprarProduto extends JFrame {

    private JTextField textFieldNome;
    private JTextField textFieldFornecedor;
    private JTextField textFieldCategoria;
    private JTextField textFieldDescricao;
    private JTextField textFieldPreco;
    private JTextField textFieldQuantidade;
    private JLabel valorTotalLabel;

    public ComprarProduto() {
        setTitle("Comprar Produto");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#2B2B2B"));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#2B2B2B"));
        form.add(FormCustomContent.create("Nome do Produto", true));
        form.add(Box.createVerticalStrut(20));
        form.add(FormCustomContent.create("Fornecedor", true));
        form.add(Box.createVerticalStrut(20));

        JPanel smallContainersPanel = new JPanel();
        smallContainersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(FormCustomSmallContent.create("Preço", true));
        smallContainersPanel.add(FormCustomSmallContent.create("Quantidade", true));
        form.add(Box.createVerticalStrut(0));
        form.add(smallContainersPanel);

        valorTotalLabel = new JLabel();
        valorTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        valorTotalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        valorTotalLabel.setForeground(Color.WHITE);
        form.add(Box.createVerticalStrut(20));
        form.add(valorTotalLabel);

        JButton calcularButton = new JButton("Valor Total");
        calcularButton.setPreferredSize(new Dimension(500, 50));
        calcularButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calcularButton.setBackground(Color.BLACK);
        calcularButton.setForeground(Color.WHITE);
        calcularButton.setFont(new Font("Arial", Font.BOLD, 14));
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularValorTotal();
            }
        });

        form.add(Box.createVerticalStrut(10));
        form.add(calcularButton);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 40));
        buttonsPanel.setBackground(Color.decode("#2B2B2B"));

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setPreferredSize(new Dimension(200, 50));
        cancelarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelarButton.setBackground(Color.RED);
        cancelarButton.setForeground(Color.WHITE);
        cancelarButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelarButton.addActionListener(e -> dispose());

        JButton comprarButton = new JButton("Comprar");
        comprarButton.setPreferredSize(new Dimension(200, 50));
        comprarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        comprarButton.setBackground(Color.decode("#2E56BE"));
        comprarButton.setForeground(Color.WHITE);
        comprarButton.setFont(new Font("Arial", Font.BOLD, 14));
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarProduto();
            }
        });

        buttonsPanel.add(cancelarButton);
        buttonsPanel.add(comprarButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(Color.decode("#2B2B2B"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.weighty = 1;
        containerPanel.add(form, gbc);
        getContentPane().add(containerPanel, BorderLayout.CENTER);
    }

    // Método para calcular o valor total
    private void calcularValorTotal() {
        try {
            String nome = textFieldNome.getText();
            String descricao = textFieldDescricao.getText();
            int quantidade = Integer.parseInt(textFieldQuantidade.getText());
            double preco = Double.parseDouble(textFieldPreco.getText());
            int categoriaId = Integer.parseInt(textFieldCategoria.getText());
            int fornecedorId = Integer.parseInt(textFieldFornecedor.getText());

            if (nome.isEmpty() || descricao.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                return;
            }

//            // Criando uma instância de Produto com os valores inseridos
//            Produto produto = new Produto(nome, descricao, quantidade, preco, categoriaId, fornecedorId);
//            double valorTotal = produto.calcularValorTotal(quantidade);
//
//            valorTotalLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para preço, quantidade, categoria e fornecedor.");
        }
    }

    // Método para comprar o produto
    private void comprarProduto() {
        // Código para comprar o produto
        JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComprarProduto telaCompra = new ComprarProduto();
            telaCompra.setVisible(true);
        });
    }
}
