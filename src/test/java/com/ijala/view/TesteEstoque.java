package com.ijala.view;

import com.ijala.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class TesteEstoque extends JFrame {
    private TablePanel customTable;

    public TesteEstoque() {
        super("Estoque");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(43, 43, 43)); // #2B2B2B
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 50));

        JLabel titleLabel = new JLabel("Gestão de Estoque");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(43, 43, 43)); // #2B2B2B

        JButton controleButton = new JButton("Controle de Produtos");
        JButton niveisEstoqueButton = new JButton("Níveis de Estoque");
        JButton reposicaoButton = new JButton("Alerta de Reposição");
        JButton movimentacaoButton = new JButton("Histórico de Movimentação");

        customButton(controleButton);
        customButton(niveisEstoqueButton);
        customButton(reposicaoButton);
        customButton(movimentacaoButton);

        buttonPanel.add(controleButton);
        buttonPanel.add(niveisEstoqueButton);
        buttonPanel.add(reposicaoButton);
        buttonPanel.add(movimentacaoButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        String[] columnNames = {"Produto", "Estoque inicial", "Entrada", "Saída", "Estoque final"};
        int[] columnWidths = {300, 150, 150, 150, 150};

        customTable = new TablePanel(columnNames, columnWidths);
        JScrollPane scrollPane = customTable.getScrollPane();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(topPanel, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void customButton(JButton botao) {
        botao.setPreferredSize(new Dimension(300, 50));
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(46, 86, 190)); // #2E56BE
        botao.setFocusPainted(false);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TesteEstoque telaProdutos = new TesteEstoque();
            telaProdutos.setVisible(true);
        });
    }
}
