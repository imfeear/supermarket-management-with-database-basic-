package com.ijala.view.compra;

import com.ijala.controller.MovimentacaoController;
import com.ijala.util.component.FormCustomContent;
import com.ijala.util.component.FormCustomSmallContent;
import com.ijala.view.produto.TabelaProdutos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComprarProduto extends JFrame {
    private int idProduto;
    private JTextField textFieldNome;
    private JTextField textFieldFornecedor;
    private JTextField textFieldCategoria;
    private JTextField textFieldDescricao;
    private JTextField textFieldPreco;
    private JTextField textFieldQuantidade;
    private JLabel valorTotalLabel;
    private MovimentacaoController movimentacaoController = new MovimentacaoController();

    public ComprarProduto() {
        setTitle("Comprar Produto");
    }
    public ComprarProduto(int idProduto, String valorNome, String valorFornecedor, String valorCategoria, String valorDescricao, String valorPreco, String valorQuantidade) {
        setTitle("Comprar Produto");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#2B2B2B"));
        movimentacaoController = new MovimentacaoController();
        this.idProduto = idProduto;
        textFieldNome = new JTextField(20);
        textFieldNome.setText(valorNome);
        textFieldNome.setEditable(false);
        textFieldFornecedor = new JTextField(20);
        textFieldFornecedor.setText(valorFornecedor);
        textFieldFornecedor.setEditable(false);
        textFieldCategoria = new JTextField(20);
        textFieldCategoria.setText(valorCategoria);
        textFieldCategoria.setEditable(false);
        textFieldDescricao = new JTextField(20);
        textFieldDescricao.setText(valorDescricao);
        textFieldDescricao.setEditable(false);
        textFieldPreco = new JTextField(20);
        textFieldPreco.setText(valorPreco);
        textFieldQuantidade = new JTextField(20);
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#2B2B2B"));
        form.add(FormCustomContent.create("Nome do Produto", textFieldNome, true));
        form.add(Box.createVerticalStrut(20));
        form.add(FormCustomContent.create("Fornecedor", textFieldFornecedor, true));
        form.add(Box.createVerticalStrut(20));

        JPanel smallContainersPanel = new JPanel();
        smallContainersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(FormCustomSmallContent.create("Preço",textFieldPreco, true));
        smallContainersPanel.add(FormCustomSmallContent.create("Quantidade" ,textFieldQuantidade, true));
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
        System.out.println(textFieldNome.getText());
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

            if ( textFieldPreco.getText().isEmpty() || textFieldQuantidade.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha os campos. Preco e quantidade");
                return;
            }

            int quantidade = Integer.parseInt(textFieldQuantidade.getText());
            double preco = Double.parseDouble(textFieldPreco.getText());
            var valorTotal = quantidade * preco;
            valorTotalLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para preço, quantidade, categoria e fornecedor.");
        }
    }

    // Método para comprar o produto
    private void comprarProduto() {
        // Código para comprar o produto
        try{
            if (this.textFieldNome.getText().isEmpty() || this.textFieldQuantidade.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                return;
            }
            movimentacaoController.Comprar(this.idProduto, Integer.parseInt(this.textFieldQuantidade.getText()));

            JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um numero valido");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComprarProduto telaProdutos = new ComprarProduto();
            telaProdutos.setVisible(true);
        });
    }

}
